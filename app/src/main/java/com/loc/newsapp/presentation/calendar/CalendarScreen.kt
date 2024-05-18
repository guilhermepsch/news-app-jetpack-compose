package com.loc.newsapp.presentation.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.Dimens.MediumPadding1
import com.loc.newsapp.presentation.common.ArticlesList
import com.loc.newsapp.presentation.common.CalendarBar

@Composable
fun CalendarScreen(
    state: CalendarState, event: (CalendarEvent) -> Unit, navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1,
            )
            .statusBarsPadding()
    ) {
        CalendarBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(CalendarEvent.UpdateSearchQuery(it)) },
            onSearch = { event(CalendarEvent.SearchNews) },
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articles?.let { it ->
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = { navigateToDetails(it) })
        }
    }
}
