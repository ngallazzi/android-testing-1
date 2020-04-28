package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import junit.framework.Assert.assertEquals
import org.junit.Test

class StatisticsUtilsTest {

    // If there's no completed task and and at least one active task should return 0%
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsZero() {
        // GIVEN a list of tasks with a single active task
        val tasks = listOf<Task>(Task("task1", "", false))
        // WHEN you call getActiveAndCompletedStats
        val result = getActiveAndCompletedStats(tasks)
        // THEN there are 0% completed tasks and 100% active tasks
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(100f, result.activeTasksPercent)
    }

    // If there's 2 completed tasks and 3 active tasks, then they are 40% completed and 60% active
    @Test
    fun getActiveAndCompletedStats_activeAndCompleted_returnsFortySixty(){
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

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        val tasks = listOf<Task>()
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_null_returnsZeros() {
        val tasks = null
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }
}