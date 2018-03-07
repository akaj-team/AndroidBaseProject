package com.app.android.ui.main

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ProgressBar
import com.app.android.R
import com.app.android.data.model.Task
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.coroutines.onMenuItemClick
import org.jetbrains.anko.appcompat.v7.titleResource
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
    internal lateinit var progressBar: ProgressBar
    internal val taskListAdapter = TaskListAdapter(tasks)

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            lparams(matchParent, matchParent)
            backgroundColor = Color.WHITE

            toolbar {
                id = R.id.mainActivityToolbar
                backgroundColor = Color.BLUE
                titleResource = R.string.mainActivityTitle
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
                    taskListAdapter.onItemClick = {
                        owner.eventTaskItemClicked(it)
                    }
                    adapter = taskListAdapter
                }
            }.lparams(matchParent, matchParent) {
                below(R.id.mainActivityToolbar)
            }

            progressBar = progressBar {
                visibility = View.GONE
            }.lparams {
                centerInParent()
            }
        }
    }
}
