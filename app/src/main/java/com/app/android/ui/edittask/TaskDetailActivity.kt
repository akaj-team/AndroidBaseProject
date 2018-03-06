package com.app.android.ui.edittask

import android.os.Bundle
import android.view.View
import com.app.android.R
import com.app.android.data.model.Task
import com.app.android.data.source.TaskRepository
import com.app.android.extension.getTimestamp
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

    companion object {
        const val KEY_TASK_ID = "task_id"
        const val DOING = 0
        const val DONE = 1
    }

    private val id by lazy { intent.getIntExtra(KEY_TASK_ID, 0) }
    private lateinit var task: Task
    private var ui = TaskDetailActivityUI()
    private lateinit var viewModel: TaskViewModel

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
        ui.btnUpdate.isEnabled = viewModel.isEnableButton(title, description)
    }

    internal fun eventOnUpdateClicked(view: View) {
        if (view.id == R.id.taskDetailActivityBtnUpdate) {
            val isDone = if (ui.rgStatus.checkedRadioButtonId == R.id.taskDetailActivityRdDone) {
                DONE
            } else {
                DOING
            }
            val createTime = task.createTime
            val updateTime = Date().getTimestamp()
            val task = Task(
                    id,
                    ui.edtTitle.text.toString().trim(),
                    ui.edtDescription.text.toString().trim(),
                    isDone,
                    createTime,
                    updateTime)

            addDisposables(viewModel.editTask(id, task)
                    .observeOnUiThread()
                    .subscribe(this::handleUpdateTaskSuccess, this::handleUpdateTaskError))
        }
    }

    internal fun eventOnDeleteClicked(view: View) {
        if (view.id == R.id.taskDetailActivityBtnDelete) {
            addDisposables(viewModel.deleteTask(id)
                    .subscribe(this::handleDeleteTaskSuccess, this::handleDeleteTaskError))
        }
    }

    private fun handleProgressBarStatus(isShow: Boolean) {
        ui.progressBar.visibility = if (isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun handleGetTaskSuccess(taskDetail: Task) {
        task = taskDetail
        updateUI()
    }

    private fun handleGetTaskError(t: Throwable) {
        toast("get task error ${t.message}")
    }

    private fun updateUI() {
        with(task) {
            ui.run {
                edtTitle.setText(title)
                edtDescription.setText(description)
                val id = if (isDone == DONE) {
                    R.id.taskDetailActivityRdDone
                } else {
                    R.id.taskDetailActivityRdDoing
                }
                rgStatus.check(id)
            }
        }
    }

    private fun handleUpdateTaskSuccess(task: Task) {
        toast("update task success")
        finish()
    }

    private fun handleUpdateTaskError(t: Throwable) {
        toast("update task error ${t.message}")
    }

    private fun handleDeleteTaskSuccess() {
        toast("delete task success")
        finish()
    }

    private fun handleDeleteTaskError(t: Throwable) {
        toast("delete task error ${t.message}")
    }
}
