package com.app.android.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.app.android.data.model.Task
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/22/18.
 */
class TaskListAdapter(private val tasks: MutableList<Task>)
    : RecyclerView.Adapter<TaskListAdapter.TaskItemViewHolder>() {

    var onItemClick: (task: Task) -> Unit = {}

    override fun getItemCount() = tasks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val ui = TaskItemUI()
        return TaskItemViewHolder(ui, ui.createView(AnkoContext.create(parent.context, parent, false)))
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder?, position: Int) {
        holder?.onBind(position)
    }

    /**
     * Item view holder of TaskListAdapter
     */
    inner class TaskItemViewHolder(private val holderUI: TaskItemUI, itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.onClick {
                onItemClick(tasks[adapterPosition])
            }
        }

        /**
         * Bind data to view holder.
         */
        fun onBind(position: Int) {
            holderUI.updateTaskItem(tasks[position])
        }
    }
}
