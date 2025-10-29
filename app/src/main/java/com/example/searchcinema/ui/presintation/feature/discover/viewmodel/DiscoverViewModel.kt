package com.example.searchcinema.ui.presintation.feature.discover.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.domain.ui.presintashion.feature.discover.interactor.DiscoverInteractor
import com.example.domain.ui.presintashion.feature.discover.model.Film
import com.example.searchcinema.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val interactor: DiscoverInteractor
): BaseViewModel<DiscoverContract.Event, DiscoverContract.State, Nothing>() {
    private val _film: MutableStateFlow<List<Film>> = MutableStateFlow(listOf())
    val film = _film.asStateFlow()

    override fun setInitialState(): DiscoverContract.State = DiscoverContract.State.Loading

    override fun handleEvent(event: DiscoverContract.Event) = when(event){
        is DiscoverContract.Event.Refresh -> {}
        is DiscoverContract.Event.FetchContent -> loadData()
        is DiscoverContract.Event.SearchNotFound -> {}
        is DiscoverContract.Event.EnableNoInternetConnectionState -> {}
    }

    init {
        loadData()
    }
    private fun loadData() {
        viewModelScope.launch(dispatcher) {
            setState(DiscoverContract.State.Loading)
            val result = interactor.getDiscover()
            _film.emit(result)
        }
    }

}