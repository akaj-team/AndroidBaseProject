package com.app.android.ui.main

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.android.R
import com.app.android.data.model.Task
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

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
        holder?.onBind()
    }

    /**
     * Item view holder of TaskListAdapter
     */
    inner class TaskItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle = itemView.find<TextView>(R.id.item_task_title)
        private val tvDescription = itemView.find<TextView>(R.id.item_task_description)
        private val tvStatus = itemView.find<TextView>(R.id.item_task_status)

        init {
            itemView.onClick {
                onItemClick(tasks[adapterPosition])
            }
        }

        /**
         * Bind data to view holder.
         */
        fun onBind() {
            with(tasks[adapterPosition]) {
                tvTitle.text = title
                tvDescription.text = description
                tvStatus.run {
                    if (isDone == 1) {
                        text = resources.getString(R.string.task_done)
                        textColor = ContextCompat.getColor(context, android.R.color.holo_green_light)
                    } else {
                        text = resources.getString(R.string.task_doing)
                        textColor = ContextCompat.getColor(context, android.R.color.holo_red_light)
                    }
                }
            }
        }
    }

    inner class TaskItemUI : AnkoComponent<ViewGroup> {

        override fun createView(ui: AnkoContext<ViewGroup>): View {
            val view = with(ui) {
                verticalLayout {
                    lparams(wrapContent, wrapContent)
                    padding = dimen(R.dimen.item_task_padding)

                    textView {
                        id = R.id.item_task_title
                        maxLines = 1
                        textColor = ContextCompat.getColor(ctx, android.R.color.black)
                        textSize = px2dip(dimen(R.dimen.item_task_tv_title_text_size))
                    }

                    textView {
                        id = R.id.item_task_description
                        maxLines = 1
                        textColor = ContextCompat.getColor(ctx, android.R.color.darker_gray)
                        textSize = px2dip(dimen(R.dimen.item_task_tv_description_text_size))
                    }

                    textView {
                        id = R.id.item_task_status
                        textSize = px2dip(dimen(R.dimen.item_task_tv_status_text_size))
                    }
                }
            }
            view.tag = TaskItemViewHolder(view)
            return view
        }
    }
}
