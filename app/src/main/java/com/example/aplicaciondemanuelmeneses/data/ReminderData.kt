package com.example.aplicaciondemanuelmeneses.data

import com.example.aplicaciondemanuelmeneses.model.*
import java.time.LocalDateTime

object ReminderData {

    // LIST: almacena nuestros recordatorios simulados.
    val reminders: List<Reminder> = listOf(

        Reminder(
            id = 1,
            title = "Entregar actividad de programación",
            description = "Finalizar proyecto Android",
            category = Category.STUDY,
            priority = Priority.HIGH,
            status = ReminderStatus.Pending,
            location = null,
            reminderDate = LocalDateTime.of(2026, 11, 10, 18, 0),
            alertEnabled = true
        ),

        Reminder(
            id = 2,
            title = "Reunión de trabajo",
            description = "Revisar tareas del equipo",
            category = Category.WORK,
            priority = Priority.MEDIUM,
            status = ReminderStatus.Pending,
            location = "Oficina principal",
            reminderDate = LocalDateTime.of(2026, 11, 12, 10, 0),
            alertEnabled = true
        ),

        Reminder(
            id = 3,
            title = "Comprar alimentos",
            description = "Compras de la semana",
            category = Category.PERSONAL,
            priority = Priority.LOW,
            status = ReminderStatus.Completed,
            location = "Supermercado",
            reminderDate = null,
            alertEnabled = false
        ),

        Reminder(
            id = 4,
            title = "Control médico",
            description = "Agendar consulta médica",
            category = Category.HEALTH,
            priority = Priority.HIGH,
            status = ReminderStatus.Overdue,
            location = "Centro médico",
            reminderDate = LocalDateTime.of(2026, 11, 2, 9, 0),
            alertEnabled = true
        )
    )

    // SET: categorías únicas, sin repetidos.
    val categories: Set<Category> =
        reminders.map { it.category }.toSet()

    // MAP: etiquetas para mostrar en pantalla.
    val categoryLabels: Map<Category, String> = mapOf(
        Category.WORK to "Trabajo",
        Category.STUDY to "Estudios",
        Category.PERSONAL to "Personal",
        Category.HEALTH to "Salud"
    )

    // MAP: colores asociados a cada categoría.
    // Se almacenan como valores ARGB.
    val categoryColors: Map<Category, Long> = mapOf(
        Category.WORK to 0xFF2196F3,
        Category.STUDY to 0xFF9C27B0,
        Category.PERSONAL to 0xFF4CAF50,
        Category.HEALTH to 0xFFFF9800
    )

    // Filtrar recordatorios pendientes.
    val pendingReminders: List<Reminder> =
        reminders.filter {
            it.status == ReminderStatus.Pending
        }

    // Filtrar recordatorios de prioridad alta.
    val highPriorityReminders: List<Reminder> =
        reminders.filter {
            it.priority == Priority.HIGH
        }
}