package com.app.android.ui.edittask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.app.android.R
import com.app.android.data.model.Task
import com.app.android.data.source.TaskRepository
import com.app.android.extension.convert
import com.app.android.extension.observeOnUiThread
import com.app.android.ui.base.BaseActivity
import com.app.android.ui.newtask.TaskViewModel
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import java.util.*

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/28/18.
 */
class TaskDetailActivity : BaseActivity() {

    private val id by lazy { intent.getIntExtra(KEY_TASK_ID, 0) }
    private var ui = TaskDetailActivityUI()
    private lateinit var viewModel: TaskViewModel

    companion object {
        const val KEY_TASK_ID = "task_id"

        fun newInstance(context: Context, id: Int): Intent {
            val intent = Intent(context, TaskDetailActivity::class.java)
            intent.putExtra(KEY_TASK_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = TaskViewModel(TaskRepository())
        ui.setContentView(this)
    }

    override fun onBindViewModel() {
        addDisposables(
                //Update progress bar status
                viewModel.progressBarStatus
                        .observeOnUiThread()
                        .subscribe(this::handleProgressBarStatus),
                //Get task detail
                viewModel.getTaskDetail(id)
                        .observeOnUiThread()
                        .subscribe(this::handleGetTaskSuccess, this::handleGetTaskError))
    }

    internal fun onHandleTextChange(title: String, description: String) {
        ui.btnUpdate.run {
            isEnabled = viewModel.isEnableUpdateButton(title, description)
        }
    }

    internal fun eventOnViewClicked(view: View) {
        when (view.id) {
            R.id.taskDetailActivityBtnUpdate -> {
                val isDone = if (ui.rgStatus.checkedRadioButtonId == R.id.taskDetailActivityRdDone) {
                    1
                } else {
                    0
                }
                val task = Task(id, ui.edtTitle.text.toString().trim(), ui.edtDescription.text.toString().trim(),
                        isDone, Date().convert(System.currentTimeMillis()), Date().convert(System.currentTimeMillis()))

                addDisposables(viewModel.editTask(id, task)
                        .observeOnUiThread()
                        .subscribe(this::handleUpdateTaskSuccess, this::handleUpdateTaskError))
            }
            R.id.taskDetailActivityBtnDelete -> {
                addDisposables(viewModel.deleteTask(id)
                        .observeOnUiThread()
                        .subscribe(this::handleDeleteTaskSuccess, this::handleDeleteTaskError))
            }
        }
    }

    private fun handleProgressBarStatus(isShow: Boolean) {
        ui.progressBar.run {
            visibility = if (isShow) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun handleGetTaskSuccess(task: Task) {
        updateUI(task)
    }

    private fun handleGetTaskError(t: Throwable) {
        toast("get task error")
    }

    private fun updateUI(task: Task) {
        with(task) {
            ui.edtTitle.run {
                setText(title)
            }
            ui.edtDescription.run {
                setText(description)
            }
            ui.rgStatus.run {
                val id = if (isDone == 1) {
                    R.id.taskDetailActivityRdDone
                } else {
                    R.id.taskDetailActivityRdDoing
                }
                check(id)
            }
        }
    }

    private fun handleUpdateTaskSuccess(task: Task) {
        toast("update task success")
        finish()
    }

    private fun handleUpdateTaskError(t: Throwable) {
        toast("update task error")
    }

    private fun handleDeleteTaskSuccess(task: Task) {
        toast("delete task success")
        finish()
    }

    private fun handleDeleteTaskError(t: Throwable) {
        toast("delete task error")
    }
}
