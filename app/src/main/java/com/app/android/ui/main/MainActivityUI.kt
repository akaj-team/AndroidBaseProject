package com.app.android.ui.main

import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ProgressBar
import com.app.android.R
import com.app.android.data.model.Task
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.coroutines.onMenuItemClick
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 *
 * @author at-vinhhuynh
 */
class MainActivityUI(tasks: MutableList<Task>) : AnkoComponent<MainActivity> {

    internal lateinit var swipeRefreshLayout: SwipeRefreshLayout
    internal val taskListAdapter = TaskListAdapter(tasks)
    internal lateinit var progressBar: ProgressBar

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            lparams(matchParent, matchParent)
            backgroundColor = ContextCompat.getColor(ctx, android.R.color.white)

            toolbar {
                id = R.id.main_activity_tool_bar
                title = resources.getString(R.string.main_activity_title)
                inflateMenu(R.menu.main_menu)

                onMenuItemClick { item ->
                    if (item?.itemId == R.id.add_task) {
                        owner.eventAddNewTaskClicked()
                    }
                }
            }.lparams(matchParent, wrapContent)

            swipeRefreshLayout = swipeRefreshLayout {
                onRefresh {
                    owner.handleSwipeRefreshLayoutOnRefresh()
                }
                recyclerView {
                    lparams(matchParent, matchParent)
                    layoutManager = LinearLayoutManager(ctx)
                    backgroundResource = android.R.color.darker_gray
                    taskListAdapter.onItemClick = {
                        owner.eventTaskItemClicked()
                    }
                    adapter = taskListAdapter
                }
            }.lparams(matchParent, matchParent) {
                below(R.id.main_activity_tool_bar)
            }

            progressBar = progressBar {
                visibility = View.GONE
            }.lparams {
                centerInParent()
            }
        }
    }
}
