package com.loc.newsapp.presentation.calendar

sealed class CalendarEvent {
    data class UpdateSearchQuery(val from: String) : CalendarEvent()
    object SearchNews : CalendarEvent()
}