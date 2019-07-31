package com.zlrx.tasktimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zlrx.tasktimer.adapter.TaskRecyclerViewAdapter
import com.zlrx.tasktimer.model.Task
import com.zlrx.tasktimer.viewmodel.TaskViewModel

class MainActivityFragment : androidx.fragment.app.Fragment(), TaskRecyclerViewAdapter.OnTaskClickListener {

    interface OnTaskEdit {
        fun onTaskEdit(task: Task)
    }

    override fun onEditClick(task: Task) {
        (activity as OnTaskEdit).onTaskEdit(task)
    }

    override fun onDeleteClick(task: Task) {
        taskViewModel.delete(task)
    }

    override fun onTaskClick(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var taskRecyclerViewAdapter: TaskRecyclerViewAdapter

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        taskViewModel = ViewModelProviders.of(activity!!).get(TaskViewModel::class.java)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun observeTasks() {
        taskViewModel.tasks.observe(this, Observer {
            taskRecyclerViewAdapter.setTasks(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.taskList)
        taskRecyclerViewAdapter = TaskRecyclerViewAdapter(this)
        recyclerView.adapter = taskRecyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        observeTasks()
    }
}
