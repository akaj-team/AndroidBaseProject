package com.app.android.ui.main

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.view.View
import com.app.android.data.source.TaskRepository
import com.app.android.extension.observeOnUiThread
import com.uniqlo.circle.ui.base.BaseActivity
import org.jetbrains.anko.setContentView

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
                //Update progress bar status
                viewModel.progressBarStatus
                        .observeOnUiThread()
                        .subscribe(this::handleProgressBarStatus),
                //Update task list
                viewModel.updateListTask
                        .observeOnUiThread()
                        .subscribe(this::handleUpdateListTask))
    }

    private fun handleProgressBarStatus(isShow: Boolean) {
        when (isShow) {
            true -> ui.progressBar.visibility = View.VISIBLE
            else -> {
                ui.progressBar.visibility = View.GONE
                ui.swipeRefreshLayout.run {
                    isRefreshing = false
                }
            }
        }
    }

    private fun handleUpdateListTask(diff: DiffUtil.DiffResult) {
        diff.dispatchUpdatesTo(ui.taskListAdapter)
    }

    internal fun handleSwipeRefreshLayoutOnRefresh() {
        viewModel.getTasks()
    }

    internal fun eventTaskItemClicked() {
    }

    internal fun eventAddNewTaskClicked() {
    }
}
