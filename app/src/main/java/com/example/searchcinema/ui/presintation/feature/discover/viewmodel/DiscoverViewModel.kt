package com.example.searchcinema.ui.presintation.feature.discover.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.domain.ui.presintashion.feature.discover.interactor.DiscoverInteractor
import com.example.domain.ui.presintashion.feature.discover.model.Film
import com.example.searchcinema.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val interactor: DiscoverInteractor
): BaseViewModel<DiscoverContract.Event, DiscoverContract.State, Nothing>() {
    private val _films: MutableStateFlow<List<Film>> = MutableStateFlow(listOf())
    val films = _films.asStateFlow()

    override fun setInitialState(): DiscoverContract.State = DiscoverContract.State.Loading

    override fun handleEvent(event: DiscoverContract.Event) = when(event){
        is DiscoverContract.Event.FetchContent -> loadData()
        is DiscoverContract.Event.Refresh -> refresh()
        is DiscoverContract.Event.EnableNoInternetConnectionState -> setState(DiscoverContract.State.NoInternetConnection)
    }

    init {
        loadData()
    }
    private fun loadData() {
        viewModelScope.launch {
            updateState { DiscoverContract.State.Loading }
            try {
                withTimeout(10000) {
                    val films = interactor.getDiscover()
                    _films.value = films
                    updateState { DiscoverContract.State.Loaded }
                }
            } catch (e: TimeoutCancellationException) {
                updateState { DiscoverContract.State.NoInternetConnection }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun refresh() = updateState { DiscoverContract.State.Loading }

    private fun handleError(exception: Throwable?) {
        when (exception) {
            is ConnectException,
            is SocketTimeoutException,
            is UnknownHostException,
            is TimeoutCancellationException -> {
                updateState { DiscoverContract.State.NoInternetConnection }
            }
            else -> {
                updateState { DiscoverContract.State.Loaded }
            }
        }
    }
}

