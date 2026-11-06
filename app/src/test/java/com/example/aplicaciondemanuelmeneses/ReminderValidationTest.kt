package com.example.aplicaciondemanuelmeneses

import com.example.aplicaciondemanuelmeneses.model.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class ReminderValidationTest {

    private fun createReminder(
        id: Int = 1,
        title: String = "Estudiar Kotlin",
        alertEnabled: Boolean = false
    ): Reminder {
        return Reminder(
            id = id,
            title = title,
            description = null,
            category = Category.STUDY,
            priority = Priority.HIGH,
            status = ReminderStatus.Pending,
            location = null,
            reminderDate = null,
            alertEnabled = alertEnabled
        )
    }

    @Test
    fun validReminderIsCreated() {
        val reminder = createReminder()

        assertEquals("Estudiar Kotlin", reminder.title)
    }

    @Test
    fun invalidIdIsRejected() {
        assertThrows(IllegalArgumentException::class.java) {
            createReminder(id = 0)
        }
    }

    @Test
    fun emptyTitleIsRejected() {
        assertThrows(IllegalArgumentException::class.java) {
            createReminder(title = "   ")
        }
    }

    @Test
    fun alertWithoutDateIsRejected() {
        assertThrows(IllegalArgumentException::class.java) {
            createReminder(alertEnabled = true)
        }
    }
}