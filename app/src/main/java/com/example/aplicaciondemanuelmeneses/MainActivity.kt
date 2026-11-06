package com.example.aplicaciondemanuelmeneses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.aplicaciondemanuelmeneses.data.ReminderData
import com.example.aplicaciondemanuelmeneses.model.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                ReminderScreen()
            }
        }
    }
}

@Composable
fun ReminderScreen() {

    val reminders = ReminderData.reminders

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "RecordatorioManuel",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Mis recordatorios",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                SummaryCard(
                    label = "Total",
                    count = reminders.size,
                    modifier = Modifier.weight(1f)
                )

                SummaryCard(
                    label = "Pendientes",
                    count = ReminderData.pendingReminders.size,
                    modifier = Modifier.weight(1f)
                )

                SummaryCard(
                    label = "Completadas",
                    count = reminders.count {
                        it.status == ReminderStatus.Completed
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Mis tareas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(
                    items = reminders,
                    key = { it.id }
                ) { reminder ->
                    ReminderCard(reminder)
                }
            }
        }
    }
}

@Composable
fun SummaryCard(
    label: String,
    count: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = count.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = label,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun ReminderCard(reminder: Reminder) {

    val categoryName =
        ReminderData.categoryLabels[reminder.category]
            ?: "Sin categoría"

    val categoryColorValue =
        ReminderData.categoryColors[reminder.category]
            ?: 0xFF808080

    val categoryColor = Color(categoryColorValue)

    val priorityText = when (reminder.priority) {
        Priority.HIGH -> "Alta"
        Priority.MEDIUM -> "Media"
        Priority.LOW -> "Baja"
    }

    val statusText = when (reminder.status) {
        ReminderStatus.Pending -> "Pendiente"
        ReminderStatus.Completed -> "Completada"
        ReminderStatus.Overdue -> "Vencida"
    }

    val statusColor = when (reminder.status) {
        ReminderStatus.Pending -> Color(0xFFFF9800)
        ReminderStatus.Completed -> Color(0xFF4CAF50)
        ReminderStatus.Overdue -> Color(0xFFF44336)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Box(
                modifier = Modifier
                    .width(5.dp)
                    .height(65.dp)
                    .background(
                        categoryColor,
                        RoundedCornerShape(4.dp)
                    )
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = reminder.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Categoría: $categoryName",
                    fontSize = 13.sp
                )

                Text(
                    text = "Prioridad: $priorityText",
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = statusText,
                    color = statusColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}