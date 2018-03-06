package com.app.android.ui.newtask

import android.os.Bundle
import android.view.View
import com.app.android.R
import com.app.android.data.model.Task
import com.app.android.data.source.TaskRepository
import com.app.android.extension.getTimestamp
import com.app.android.extension.observeOnUiThread
import com.app.android.ui.base.BaseActivity
import com.app.android.ui.edittask.TaskDetailActivity.Companion.DOING
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import java.util.*

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/28/18.
 */
class NewTaskActivity : BaseActivity() {

    private var ui = NewTaskActivityUI()
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
                        .subscribe(this::handleProgressBarStatus))
    }

    internal fun onHandleTextChange(title: String, description: String) {
        ui.btnSubmit.isEnabled = viewModel.isEnableButton(title, description)
    }

    internal fun eventOnSubmitClicked(view: View) {
        if (view.id == R.id.newTaskActivityBtnSubmit) {
            val time = Date().getTimestamp()
            val task = Task(
                    0,
                    ui.edtTitle.text.toString().trim(),
                    ui.edtDescription.text.toString().trim(),
                    DOING,
                    time,
                    time)
            addDisposables(viewModel.createTask(task)
                    .observeOnUiThread()
                    .subscribe(this::handleCreateTaskSuccess, this::handleCreateTaskError))
        }
    }

    private fun handleProgressBarStatus(isShow: Boolean) {
        ui.progressBar.visibility = if (isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun handleCreateTaskSuccess(task: Task) {
        finish()
    }

    private fun handleCreateTaskError(t: Throwable) {
        toast("create task error ${t.message}")
    }
}
