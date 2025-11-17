package com.example.searchcinema.ui.presintation.feature.detail.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.domain.ui.presintashion.feature.Film
import com.example.domain.ui.presintashion.feature.detail.interactot.DetailInteractor
import com.example.searchcinema.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    override fun setInitialState(): DetailContract.State = DetailContract.State.Loaded

    override fun handleEvent(event: DetailContract.Event) = when(event){
        is DetailContract.Event.FetchContent -> loadData(event.filmId)
    }
    fun loadData(filmId: Int) {
        viewModelScope.launch(dispatcher) {
            interactor.getFilmId(filmId = filmId).fold(
                onSuccess = { film ->
                    _film.emit(film)

                    interactor.getFilm().fold(
                        onSuccess = { allFilms ->
                            val films = allFilms.filter { it.id != filmId }
                            _films.emit(films)
                        },
                        onFailure = { error ->
                            _films.emit(emptyList())
                        }
                    )
                    updateState { DetailContract.State.Loaded }
                },
                onFailure = {
                }
            )
        }
    }

}