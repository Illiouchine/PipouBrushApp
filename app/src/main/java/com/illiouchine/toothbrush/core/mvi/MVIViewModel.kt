package com.illiouchine.toothbrush.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * This View Model follow the MVI pattern.
 * The View should only trigger the dispatchIntent() method
 * The View should observe uiState and event
 *
 * flow of action
 * 1. Intent -> 2. Action -> 3. PartialState -> 4. State
 *
 * 1. dispatched intent will be mapped into action
 * 2. Action will process somme external code and generate a Partial State
 * 3. Partial State will be reduced into a new State
 *
 * With the StateFlow, events are broadcast to an unknown number (zero or more) of subscribers.
 * Always emit somethings
 *
 * With the shared flow, events are broadcast to an unknown number (zero or more) of subscribers.
 * In the absence of a subscriber, any posted event is immediately dropped.
 * It is a design pattern to use for events that must be processed immediately or not at all.
 *
 * With the channel, each event is delivered to a single subscriber.
 * An attempt to post an event without subscribers will suspend as soon as the channel buffer becomes full,
 * waiting for a subscriber to appear. Posted events are never dropped by default.
 *
 */
abstract class MviViewModel
<Intent : UiIntent,
        State : UiState,
        Event : UiEvent,
        PartialState : UiPartialState,
        Action : UiAction
        > :
    ViewModel() {

    // Create Initial State of View
    private val initialState: State by lazy { createInitialState() }
    protected abstract fun createInitialState(): State

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()
    private val intent = _intent.asSharedFlow()

    private val _event: Channel<Event> = Channel()
    val event = _event.receiveAsFlow()

    private val partialStateChannel = Channel<PartialState>()
    private val actionChannel = Channel<Action>()

    private val reducer: Reducer<State, PartialState> by lazy { createReducer() }
    protected abstract fun createReducer(): Reducer<State, PartialState>

    init {
        observeAction()
        observeReducer()
        subscribeUserIntent()
    }

    private fun observeAction() {
        viewModelScope.launch {
            for (action in actionChannel) {
                handleAction(action)
            }
        }
    }

    private fun observeReducer() {
        viewModelScope.launch {
            for (partialState in partialStateChannel) {
                val newState = reducer.reduce(uiState.value, partialState)
                _uiState.value = newState
            }
        }
    }

    /**
     * Subscribe to User Intent trigger by the View
     * Map it into Action and publish it into actionChannel
     */
    private fun subscribeUserIntent() {
        viewModelScope.launch {
            intent.collect {
                val action = handleUserIntent(it)
                actionChannel.send(action)
            }
        }
    }

    /**
     * Should map user Intent into action
     */
    abstract fun handleUserIntent(intent: Intent): Action

    /**
     * This process code and should only trigger :
     * setEvent { Event() }
     * or
     * setPartialState { PartialState() }
     */
    protected abstract suspend fun handleAction(action: Action)

    protected fun setEvent(builder: () -> Event) {
        val eventValue = builder()
        viewModelScope.launch { _event.send(eventValue) }
    }

    protected fun setPartialState(builder: () -> PartialState) {
        val newPartialState = builder()
        viewModelScope.launch { partialStateChannel.send(newPartialState) }
    }

    protected fun setAction(builder: () -> Action) {
        val newAction = builder()
        viewModelScope.launch { actionChannel.send(newAction) }
    }

    /**
     * Dispatch Intent
     */
    fun dispatchIntent(intent: Intent) {
        val newIntent = intent
        viewModelScope.launch { _intent.emit(newIntent) }
    }
}

abstract class Reducer<UiState, UiPartialState> {
    abstract fun reduce(currentState: UiState, partialState: UiPartialState): UiState
}

// User Action
interface UiIntent
// Action
interface UiAction
// PartialState of View ? should be here ?
interface UiPartialState
// State of View
interface UiState

// Effect which we want to show only one
interface UiEvent
