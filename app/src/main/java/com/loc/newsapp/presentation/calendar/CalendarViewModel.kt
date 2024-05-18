package com.loc.newsapp.presentation.calendar

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(CalendarState())
    val state: State<CalendarState> = _state

    fun onEvent(event: CalendarEvent) {
        when (event) {
            is CalendarEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(from = event.from)
            }

            is CalendarEvent.SearchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles = newsUseCases.calendarNews(
            from = state.value.from,
            sources = listOf("bbc-news", "abc-news")
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)
    }
}