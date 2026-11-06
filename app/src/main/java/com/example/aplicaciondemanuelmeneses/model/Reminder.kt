package com.example.aplicaciondemanuelmeneses.model

import java.time.LocalDateTime

data class Reminder(
    val id: Int,
    val title: String,
    val description: String?,
    val category: Category,
    val priority: Priority,
    val status: ReminderStatus,
    val location: String?,
    val reminderDate: LocalDateTime?,
    val alertEnabled: Boolean
) {
    init {
        require(id > 0) {
            "El ID debe ser mayor que cero"
        }

        require(title.isNotBlank()) {
            "El título no puede estar vacío"
        }

        require(!alertEnabled || reminderDate != null) {
            "Una alerta activada necesita una fecha"
        }
    }
}