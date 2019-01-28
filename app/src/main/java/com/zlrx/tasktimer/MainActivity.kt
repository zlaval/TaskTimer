package com.zlrx.tasktimer

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zlrx.tasktimer.fragment.AddEditTaskFragment
import com.zlrx.tasktimer.model.Task
import com.zlrx.tasktimer.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), AddEditTaskFragment.OnSaveClicked {

    private var twoPane = false

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        twoPane = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        restoreFragment()
        observeTasks()
    }

    private fun observeTasks() {
        taskViewModel.tasks.observe(this, Observer {
            it?.forEach { task ->
                Log.i("TASK", task.toString())
            }
        })
    }

    private fun restoreFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.taskDetailsContainer)
        if (fragment != null) {
            showEditPane()
        } else {
            showMainPane()
        }
    }

    private fun showEditPane() {
        taskDetailsContainer.visibility = View.VISIBLE
        mainFragment.view?.visibility = if (twoPane) View.VISIBLE else View.GONE
    }

    private fun showMainPane() {
        taskDetailsContainer.visibility = if (twoPane) View.INVISIBLE else View.GONE
        mainFragment.view?.visibility = View.VISIBLE
    }

    private fun removeEditPane(fragment: Fragment? = null) {
        fragment?.let {
            supportFragmentManager.beginTransaction()
                .remove(it)
                .commit()
        }
        showMainPane()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onSaveClicked() {
        val fragment = supportFragmentManager.findFragmentById(R.id.taskDetailsContainer)
        removeEditPane(fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mainMenuAddTask -> taskEditRequest(null)
            android.R.id.home -> {
                val fragment = supportFragmentManager.findFragmentById(R.id.taskDetailsContainer)
                removeEditPane(fragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskEditRequest(task: Task?) {
        val newFragment = AddEditTaskFragment.newInstance(task)
        supportFragmentManager.beginTransaction()
            .replace(R.id.taskDetailsContainer, newFragment)
            .commit()
        showEditPane()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.taskDetailsContainer)
        if (twoPane || fragment == null) {
            super.onBackPressed()
        } else {
            removeEditPane(fragment)
        }
    }

}

