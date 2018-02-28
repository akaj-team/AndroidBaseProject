package com.app.android.ui.main

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.android.R
import com.app.android.data.model.Task
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textResource

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/22/18.
 */
class TaskListAdapter(val tasks: MutableList<Task>)
    : RecyclerView.Adapter<TaskListAdapter.TaskItemViewHolder>() {

    var onItemClick: (task: Task) -> Unit = {}

    override fun getItemCount() = tasks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = TaskItemUI().createView(AnkoContext.create(parent.context, parent, false)).tag as? TaskItemViewHolder

    override fun onBindViewHolder(holder: TaskItemViewHolder?, position: Int) {
        holder?.onBind(position)
    }

    /**
     * Item view holder of TaskListAdapter
     */
    inner class TaskItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle = itemView.find<TextView>(R.id.itemTaskTitle)
        private val tvDescription = itemView.find<TextView>(R.id.itemTaskDescription)
        private val tvStatus = itemView.find<TextView>(R.id.itemTaskStatus)

        init {
            itemView.onClick {
                onItemClick(tasks[adapterPosition])
            }
        }

        /**
         * Bind data to view holder.
         */
        fun onBind(position: Int) {
            with(tasks[position]) {
                tvTitle.text = title
                tvDescription.text = description
                tvStatus.run {
                    if (isDone == 1) {
                        textResource = R.string.taskDone
                        textColor = Color.GREEN
                    } else {
                        textResource = R.string.taskDoing
                        textColor = Color.RED
                    }
                }
            }
        }
    }
}
