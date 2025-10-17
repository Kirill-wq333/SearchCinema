package com.example.searchcinema.ui.presintation.feature.discover.viewmodel

import com.example.searchcinema.core.viewmodel.ViewEvent
import com.example.searchcinema.core.viewmodel.ViewState

object DiscoverContract {

    sealed interface Event : ViewEvent {
        data object FetchContent: Event
        data object Refresh: Event
        data object EnableNoInternetConnectionState: Event
        data object SearchNotFound: Event
    }

    sealed interface State : ViewState{
        data object Loaded: State
        data object Loading: State
        data object NoInternetConnection: State
        data object NotFound: State
    }
}