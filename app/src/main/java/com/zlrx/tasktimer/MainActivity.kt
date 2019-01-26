package com.zlrx.tasktimer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
        // observeTasks()

    }

    private fun removeEditPane(fragment: Fragment? = null) {
        fragment?.let {
            supportFragmentManager.beginTransaction()
                .remove(it)
                .commit()
        }
        taskDetailsContainer.visibility = if (twoPane) View.INVISIBLE else View.GONE
        mainFragment.view?.visibility = View.VISIBLE
    }

    override fun onSaveClicked() {
        val fragment = supportFragmentManager.findFragmentById(R.id.taskDetailsContainer)
        removeEditPane(fragment)
    }


//    private fun observeTasks() {
//        taskViewModel.tasks.observe(this, Observer {
//            it?.forEach { task ->
//                Log.i("TASK", task.toString())
//            }
//        })
//    }
//
//    fun insertTask(view: View) {
//        val task = Task(name = "Task")
//        taskViewModel.insert(task)
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.mainMenuAddTask -> taskEditRequest(null)
            //  R.id.mainMenuSettings -> true

        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskEditRequest(task: Task?) {
        val newFragment = AddEditTaskFragment.newInstance(task)
        supportFragmentManager.beginTransaction()
            .replace(R.id.taskDetailsContainer, newFragment)
            .commit()
    }
}
