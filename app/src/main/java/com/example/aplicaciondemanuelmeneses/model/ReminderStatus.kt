package com.example.aplicaciondemanuelmeneses.model

sealed class ReminderStatus {

    data object Pending : ReminderStatus()

    data object Completed : ReminderStatus()

    data object Overdue : ReminderStatus()
}