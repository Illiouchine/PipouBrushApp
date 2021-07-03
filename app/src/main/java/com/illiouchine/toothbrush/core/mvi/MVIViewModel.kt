package com.illiouchine.toothbrush.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * With the StateFlow, Todo ....
 *
 * With the shared flow, events are broadcast to an unknown number (zero or more) of subscribers.
 * In the absence of a subscriber, any posted event is immediately dropped.
 * It is a design pattern to use for events that must be processed immediately or not at all.
 *
 * With the channel, each event is delivered to a single subscriber.
 * An attempt to post an event without subscribers will suspend as soon as the channel buffer becomes full,
 * waiting for a subscriber to appear. Posted events are never dropped by default.
 */
abstract class MVIViewModel<Intent : UiIntent, State : UiState, Event : UiEvent> : ViewModel() {

    // Create Initial State of View
    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    // Get Current State
    private val currentState: State
        get() = uiState.value

    private val _uiState : MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _intent : MutableSharedFlow<Intent> = MutableSharedFlow()
    val intent = _intent.asSharedFlow()

    private val _event : Channel<Event> = Channel()
    val event = _event.receiveAsFlow()

    init {
        subscribeUserIntent()
    }

    private fun subscribeUserIntent() {
        viewModelScope.launch {
            intent.collect{
                handleUserIntent(it)
            }
        }
    }

    abstract fun handleUserIntent(event: Intent)

    /**
     * Dispatch Intent
     */
    fun dispatchIntent(intent : Intent) {
        val newIntent = intent
        viewModelScope.launch { _intent.emit(newIntent) }
    }

    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    /**
     * Set new Event
     */
    protected fun setEvent(builder: () -> Event) {
        val eventValue = builder()
        viewModelScope.launch { _event.send(eventValue) }
    }

}

// State of View
interface UiState

// User Action
interface UiIntent

// Effect which we want to show only one
interface UiEvent