package com.app.android.ui.main

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.view.View
import com.app.android.data.model.Task
import com.app.android.data.source.TaskRepository
import com.app.android.extension.observeOnUiThread
import com.app.android.ui.base.BaseActivity
import com.app.android.ui.task.edittask.TaskDetailActivity
import com.app.android.ui.task.newtask.NewTaskActivity
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    private lateinit var ui: MainActivityUI
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModel(TaskRepository())
        ui = MainActivityUI(viewModel.getTasks())
        ui.setContentView(this)
    }

    override fun onBindViewModel() {
        addDisposables(
                //Update progress bar status
                viewModel.getProgressbarStatus()
                        .observeOnUiThread()
                        .subscribe(this::handleProgressBarStatus),
                //Update task list
                viewModel.updateListTask()
                        .observeOnUiThread()
                        .subscribe(this::handleUpdateListTask),
                viewModel.getListTask()
                        .observeOnUiThread()
                        .subscribe())
    }

    private fun handleProgressBarStatus(isShow: Boolean) = if (isShow) {
        ui.progressBar.visibility = View.VISIBLE
    } else {
        ui.run {
            progressBar.visibility = View.GONE
            swipeRefreshLayout.run {
                isRefreshing = false
            }
        }
    }

    private fun handleUpdateListTask(diff: DiffUtil.DiffResult) {
        diff.dispatchUpdatesTo(ui.taskListAdapter)
    }

    internal fun handleSwipeRefreshLayoutOnRefresh() {
        addDisposables(viewModel.getListTask()
                .observeOnUiThread()
                .subscribe())
    }

    internal fun eventTaskItemClicked(task: Task) {
        startActivity<TaskDetailActivity>(TaskDetailActivity.KEY_TASK_ID to task.id)
    }

    internal fun eventAddNewTaskClicked() {
        startActivity<NewTaskActivity>()
    }
}
