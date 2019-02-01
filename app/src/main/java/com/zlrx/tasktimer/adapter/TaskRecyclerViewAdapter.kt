package com.zlrx.tasktimer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zlrx.tasktimer.R
import com.zlrx.tasktimer.model.Task

class TaskItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val taskName: TextView = view.findViewById(R.id.taskItemName)
    val taskDescripton: TextView = view.findViewById(R.id.taskItemDescription)
}

class TaskRecyclerViewAdapter : RecyclerView.Adapter<TaskItemViewHolder>() {

    private var tasks: List<Task> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list_items, parent, false)
        return TaskItemViewHolder(view)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskName.text = task.name
        holder.taskDescripton.text = task.description
    }

    fun setTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

}