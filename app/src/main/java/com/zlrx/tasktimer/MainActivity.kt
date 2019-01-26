package com.zlrx.tasktimer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.zlrx.tasktimer.fragment.AddEditTaskFragment
import com.zlrx.tasktimer.model.Task
import com.zlrx.tasktimer.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AddEditTaskFragment.OnSaveClicked {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        // observeTasks()

    }

    override fun onSaveClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
            .replace(R.id.fragment, newFragment)
            .commit()
    }
}
