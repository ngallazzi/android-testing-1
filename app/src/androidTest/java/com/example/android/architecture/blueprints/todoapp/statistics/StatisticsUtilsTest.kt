package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import junit.framework.Assert.assertEquals
import org.junit.Test

class StatisticsUtilsTest {

    // If there's no completed task and and at least one active task should return 0%
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsZero() {
        val tasks = listOf<Task>(Task("task1", "", false))
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(100f, result.activeTasksPercent)
    }
}