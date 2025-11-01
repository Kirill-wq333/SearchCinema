package com.example.searchcinema.ui.presintation.feature.detail.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.domain.ui.presintashion.feature.detail.interactot.DetailInteractor
import com.example.domain.ui.presintashion.feature.discover.model.Film
import com.example.searchcinema.core.viewmodel.BaseViewModel
import com.example.searchcinema.ui.presintation.feature.discover.viewmodel.DiscoverContract
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
class DetailViewModel @Inject constructor(
    private val interactor: DetailInteractor
): BaseViewModel<DetailContract.Event, DetailContract.State, Nothing>(){

    private val _films: MutableStateFlow<List<Film>> = MutableStateFlow(listOf())
    val films = _films.asStateFlow()

    private val _film: MutableStateFlow<Film?> = MutableStateFlow(null)
    val film = _film.asStateFlow()

    override fun setInitialState(): DetailContract.State = DetailContract.State.Loading

    override fun handleEvent(event: DetailContract.Event) = when(event){
        is DetailContract.Event.FetchContent -> loadData(event.filmId)
        is DetailContract.Event.Refresh -> refresh()
        is DetailContract.Event.EnableNoInternetConnectionState -> setState(DetailContract.State.NoInternetConnection)
    }
    fun loadData(filmId: Int) {
        viewModelScope.launch {
            updateState { DetailContract.State.Loading }
            try {
                _film.emit(interactor.getFilmId(filmId))
                _films.emit(interactor.getFilm())
                updateState { DetailContract.State.Loaded }
            } catch (e: TimeoutCancellationException) {
                updateState { DetailContract.State.NoInternetConnection }
            } catch (e: Exception) {
                handleError(e)
            }
            updateState { DetailContract.State.Loaded }
        }
    }

    private fun refresh() = updateState { DetailContract.State.Loading }

    private fun handleError(exception: Throwable?) {
        when (exception) {
            is ConnectException,
            is SocketTimeoutException,
            is UnknownHostException,
            is TimeoutCancellationException -> {
                updateState { DetailContract.State.NoInternetConnection }
            }
            else -> {
                updateState { DetailContract.State.Loaded }
            }
        }
    }

}