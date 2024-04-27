package com.loc.newsapp.presentation.navgraph

sealed class Route(
    val route: String
) {

    object OnBoardingScreen : Route(route = "OnBoardingScreen")
    object HomeScreen : Route(route = "HomeScreen")
    object SearchScreen : Route(route = "SearchScreen")
    object BookmarkScreen : Route(route = "BookmarkScreen")
    object DetailsScreen : Route(route = "DetailsScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object NewsNavigation : Route(route = "newsNavigation")
    object NewsNavigatorScreen : Route(route = "newsNavigatorScreen")

}