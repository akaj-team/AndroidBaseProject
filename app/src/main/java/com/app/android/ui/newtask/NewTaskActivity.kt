package com.app.android.ui.newtask

import android.os.Bundle
import android.view.View
import com.app.android.R
import com.app.android.data.model.Task
import com.app.android.data.source.TaskRepository
import com.app.android.extension.observeOnUiThread
import com.uniqlo.circle.ui.base.BaseActivity
import org.jetbrains.anko.setContentView

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/28/18.
 */
class NewTaskActivity : BaseActivity() {

    private var ui = NewTaskActivityUI()
    private lateinit var viewModel: NewTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = NewTaskViewModel(TaskRepository())
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
        ui.btnSubmit.run {
            isEnabled = viewModel.isEnableSubmitButton(title, description)
        }
    }

    internal fun eventOnViewClicked(view: View) {
        if (view.id == R.id.newTaskActivityBtnSubmit) {
            val task = Task(0, ui.edtTitle.text.toString().trim(), ui.edtDescription.text.toString().trim(),
                    0, System.currentTimeMillis().toString(), System.currentTimeMillis().toString())
            addDisposables(viewModel.createTask(task)
                    .observeOnUiThread()
                    .subscribe(this::handleCreateTaskSuccess, this::handleCreateTaskError))
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

    private fun handleCreateTaskSuccess(task: Task) {

    }

    private fun handleCreateTaskError(t: Throwable) {

    }
}
