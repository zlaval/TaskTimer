package com.zlrx.tasktimer.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zlrx.tasktimer.R
import com.zlrx.tasktimer.model.Task
import kotlinx.android.synthetic.main.fragment_add_edit.*

private const val TAG = "AddEditTaskFragment"
private const val ARG_TASK = "task"

class AddEditTaskFragment : Fragment() {

    private var task: Task? = null

    private var listener: OnSaveClicked? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate starts")
        super.onCreate(savedInstanceState)
        task = arguments?.getParcelable(ARG_TASK)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnEditTaskSave.setOnClickListener {
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
