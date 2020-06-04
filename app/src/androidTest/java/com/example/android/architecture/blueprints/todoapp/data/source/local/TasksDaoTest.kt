package com.example.android.architecture.blueprints.todoapp.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.android.architecture.blueprints.todoapp.data.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TasksDaoTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ToDoDatabase

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ToDoDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertTaskAndGetById() = runBlockingTest {
        // GIVEN - insert a task
        val task = Task("title", "description")
        database.taskDao().insertTask(task)

        // WHEN - get the task by id
        val loaded = database.taskDao().getTaskById(task.id)

        // THEN - The loaded data contains the expected values
        assertThat(loaded?.id, `is`(task.id))
    }

    @Test
    fun updateTaskAndGetById() = runBlockingTest {
        // 1. Insert a task into the DAO.
        val originalTask = Task("title_1", "description", false, "1")
        database.taskDao().insertTask(originalTask)
        // 2. Update the task by creating a new task with the same ID but different attributes.
        val updatedTask = Task("title_2", "description", false, "1")
        database.taskDao().updateTask(updatedTask)
        // 3. Check that when you get the task by its ID, it has the updated values.
        val updated = database.taskDao().getTaskById("1")
        assertThat(updated?.title, `is`(updatedTask.title))
    }
}