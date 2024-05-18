package com.loc.newsapp.presentation.calendar

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class CalendarState(
    val from: String = "",
    val articles: Flow<PagingData<Article>>? = null
) {

}