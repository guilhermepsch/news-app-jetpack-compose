package com.loc.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class CalendarNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(from: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.calendarNews(from = from, sources = sources)
    }
}