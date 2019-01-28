package com.zlrx.tasktimer.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.zlrx.tasktimer.R
import com.zlrx.tasktimer.model.Task
import com.zlrx.tasktimer.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add_edit.*

private const val TAG = "AddEditTaskFragment"
private const val ARG_TASK = "task"

class AddEditTaskFragment : Fragment() {

    private var task: Task? = null

    private var listener: OnSaveClicked? = null

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate starts")
        super.onCreate(savedInstanceState)
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        task = arguments?.getParcelable(ARG_TASK)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            if (task != null) {
                with(task!!) {
                    editTaskName.setText(name)
                    editTaskDescription.setText(description)
                    editTaskSortOrder.setText(sortOrder?.toString())
                }
            }
        }
    }

    private fun saveTask() {
        val task = task
        if (task != null) {
            val updatable = setTaskValues(task)
            taskViewModel.update(updatable)
        } else {
            val insertable = setTaskValues(Task())
            taskViewModel.insert(insertable)
        }
    }

    //TODO task should be immutable and not modify the param
    private fun setTaskValues(task: Task): Task {
        task.name = editTaskName.text.toString()
        task.description = editTaskDescription.text.toString()
        task.sortOrder = if (editTaskSortOrder.text.isNotBlank())
            Integer.parseInt(editTaskSortOrder.text.toString())
        else
            null
        return task
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val actionBar = (listener as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        btnEditTaskSave.setOnClickListener {
            saveTask()
            listener?.onSaveClicked()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSaveClicked) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnSaveClicked")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnSaveClicked {
        fun onSaveClicked()
    }

    companion object {

        @JvmStatic
        fun newInstance(task: Task?) =
            AddEditTaskFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_TASK, task)
                }
            }
    }

}
