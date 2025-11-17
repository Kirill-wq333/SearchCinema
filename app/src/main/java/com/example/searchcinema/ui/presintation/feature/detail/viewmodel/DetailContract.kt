package com.example.searchcinema.ui.presintation.feature.detail.viewmodel

import com.example.searchcinema.core.viewmodel.ViewEvent
import com.example.searchcinema.core.viewmodel.ViewState

object DetailContract {
    sealed interface Event : ViewEvent {
        data class FetchContent(val filmId: Int): Event
    }

    sealed interface State : ViewState{
        data object Loaded: State
    }
}