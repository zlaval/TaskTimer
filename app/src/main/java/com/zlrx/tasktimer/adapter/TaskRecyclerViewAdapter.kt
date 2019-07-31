package com.zlrx.tasktimer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zlrx.tasktimer.R
import com.zlrx.tasktimer.model.Task

private const val TAG = "TaskItemViewHolder"

class TaskItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val taskName: TextView = view.findViewById(R.id.taskItemName)
    val taskDescripton: TextView = view.findViewById(R.id.taskItemDescription)
    val editBtn: ImageButton = view.findViewById(R.id.editTaskItem)
    val deleteBtn: ImageButton = view.findViewById(R.id.deleteTaskItem)

    fun bind(task: Task, listener: TaskRecyclerViewAdapter.OnTaskClickListener) {
        editBtn.setOnClickListener {
            listener.onEditClick(task)
        }
        deleteBtn.setOnClickListener {
            listener.onDeleteClick(task)
        }

        view.setOnLongClickListener {
            listener.onTaskClick(task)
            true
        }
    }
}

class TaskRecyclerViewAdapter(private val listener: OnTaskClickListener) : RecyclerView.Adapter<TaskItemViewHolder>() {

    interface OnTaskClickListener {
        fun onEditClick(task: Task)
        fun onDeleteClick(task: Task)
        fun onTaskClick(task: Task)
    }

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
        holder.bind(task, listener)
    }

    fun setTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

}