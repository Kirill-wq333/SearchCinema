package com.example.searchcinema.ui.presintation.feature.discover.viewmodel

import com.example.domain.ui.presintashion.feature.discover.model.FilmList
import com.example.domain.ui.presintashion.feature.discover.repository.DiscoverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: DiscoverRepository
) {
    private val _courses = MutableStateFlow<FilmList>(FilmList(emptyList()))
    val courses: StateFlow<FilmList> = _courses


}