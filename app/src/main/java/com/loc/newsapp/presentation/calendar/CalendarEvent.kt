package com.loc.newsapp.presentation.calendar

sealed class CalendarEvent {
    data class UpdateSearchQuery(val searchQuery: String) : CalendarEvent()
    object SearchNews : CalendarEvent()
}