package com.illiouchine.toothbrush.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class ReduxViewModel<Intent : UiIntent, State : UiState, Event : UiEvent, PartialState: UiPartialState, Action: UiAction> : ViewModel() {

    // Create Initial State of View
    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    // Get Current State
    private val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    private val uiState = _uiState.asStateFlow()

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()
    val intent = _intent.asSharedFlow()

    private val _event: Channel<Event> = Channel()
    val event = _event.receiveAsFlow()

    private val stateChannel = Channel<PartialState>()

    private val reducer: Reducer<State, PartialState> by lazy { createReducer() }
    abstract fun createReducer(): Reducer<State, PartialState>

    init {
        subscribeUserIntent()
        launchReducer()
    }

    private fun launchReducer() {
        viewModelScope.launch {
            for (partialState in stateChannel){
                _uiState.value = reducer.reduce(currentState, partialState)
            }
        }
    }

    private fun subscribeUserIntent() {
        viewModelScope.launch {
            intent
                    .map { handleUserIntent(it) }
                    .map { handleAction(it) }
                    .collect {
                        stateChannel.send(it)
                    }
        }
    }

    abstract fun handleUserIntent(intent: Intent): Action

    abstract fun handleAction(action: Action): PartialState

    /**
     * Dispatch Intent
     */
    fun dispatchIntent(intent: Intent) {
        val newIntent = intent
        viewModelScope.launch { _intent.emit(newIntent) }
    }

    /**
     * Set new Event
     */
    protected fun setEvent(builder: () -> Event) {
        val eventValue = builder()
        viewModelScope.launch { _event.send(eventValue) }
    }
}

abstract class Reducer<UiState, UiPartialState>{
    abstract fun reduce(currentState: UiState, partialState: UiPartialState) : UiState
}


interface UiAction

interface UiPartialState
