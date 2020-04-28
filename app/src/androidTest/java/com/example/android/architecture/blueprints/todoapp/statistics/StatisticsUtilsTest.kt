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

    // If there's 2 completed tasks and 3 active tasks, then they are 40% completed and 60% active
    @Test
    fun getActiveAndCompletedStats_ActiveAndCompleted(){
        val tasks = listOf<Task>(Task("task1", "", true),
                Task("task2", "", true),
                Task("task3", "", false),
                Task("task4", "", false),
                Task("task5", "", false)
        )
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(40f, result.completedTasksPercent)
        assertEquals(60f, result.activeTasksPercent)
    }
}