package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf<Page>(
    Page(
        "O seu aplicativo de notícias favorito",
        "Encontre rapidamente as notícias que deseja ler, pesquisando por título ou explorando o calendário de publicações.",
        R.drawable.onboarding1
    ),
    Page(
        "Salve suas notícias",
        "Com o Saber+, você pode marcar suas notícias favoritas para ler mais tarde, seja no ônibus, no trabalho ou em casa.",
        R.drawable.onboarding2
    ),
    Page(
        "Compartilhe e visualize facilmente",
        "Compartilhe notícias interessantes com seus amigos e familiares e visualize-as de forma rápida e conveniente.",
        R.drawable.onboarding3
    )
)
