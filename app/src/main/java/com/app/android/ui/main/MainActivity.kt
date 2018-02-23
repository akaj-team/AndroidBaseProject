package com.app.android.ui.main

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.view.View
import com.app.android.data.model.Task
import com.app.android.data.source.TaskRepository
import com.app.android.extension.observeOnUiThread
import com.uniqlo.circle.ui.base.BaseActivity
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    private lateinit var ui: MainActivityUI
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainActivityViewModel(TaskRepository())
        ui = MainActivityUI(viewModel.tasks)
        ui.setContentView(this)
        viewModel.getTasks()
    }

    override fun onBindViewModel() {
        addDisposables(
                //Get tasks
                viewModel.taskObservable
                        .observeOnUiThread()
                        .subscribe(this::handleGetTasksComplete, this::handleGetTasksError),
                //Update progress bar status
                viewModel.progressBarStatus
                        .observeOnUiThread()
                        .subscribe(this::handleProgressBarStatus),
                //Update task list
                viewModel.updateListTask
                        .observeOnUiThread()
                        .subscribe(this::handleUpdateListTask))
    }

    internal fun handleSwipeRefreshLayoutOnRefresh() {
        viewModel.getTasks()
    }

    internal fun eventTaskItemClicked() {
    }

    internal fun eventAddNewTaskClicked() {
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

    private fun handleUpdateListTask(diff: DiffUtil.DiffResult) {
        diff.dispatchUpdatesTo(ui.taskListAdapter)
    }

    private fun handleGetTasksComplete(tasks: List<Task>) {
        toast("get tasks completed")
        ui.swipeRefreshLayout.run {
            isRefreshing = false
        }
    }

    private fun handleGetTasksError(t: Throwable) {
        toast("get tasks failure " + t.message)
        ui.swipeRefreshLayout.run {
            isRefreshing = false
        }
    }
}
