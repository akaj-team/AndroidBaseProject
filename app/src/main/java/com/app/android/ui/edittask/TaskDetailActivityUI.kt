package com.app.android.ui.edittask

import android.graphics.Color
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioGroup
import com.app.android.R
import com.app.android.extension.hideKeyboard
import com.app.android.extension.onTextChangeListener
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onEditorAction

/**
 *
 * @author at-vinhhuynh
 */
class TaskDetailActivityUI : AnkoComponent<TaskDetailActivity> {

    internal lateinit var progressBar: ProgressBar
    internal lateinit var edtTitle: EditText
    internal lateinit var edtDescription: EditText
    internal lateinit var btnUpdate: Button
    internal lateinit var btnDelete: Button
    internal lateinit var rgStatus: RadioGroup

    override fun createView(ui: AnkoContext<TaskDetailActivity>) = with(ui) {
        relativeLayout {
            lparams(matchParent, matchParent)
            backgroundColor = Color.WHITE
            padding = dimen(R.dimen.newTaskRlPadding)

            edtTitle = editText {
                id = R.id.taskDetailActivityEdtTitle
                hintResource = R.string.newTaskHintTitle
                inputType = InputType.TYPE_CLASS_TEXT
                textSize = px2dip(dimen(R.dimen.newTaskTvTitleTextSize))
                maxLines = 1

                onEditorAction { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        hideKeyboard(ctx)
                    }
                }
            }.lparams(matchParent, wrapContent) {
                topMargin = dimen(R.dimen.newTaskTvTitleMarginTop)
            }

            edtDescription = editText {
                id = R.id.taskDetailActivityEdtDescription
                hintResource = R.string.newTaskHintDescription
                inputType = InputType.TYPE_CLASS_TEXT
                textSize = px2dip(dimen(R.dimen.newTaskTvDescriptionTextSize))
                maxLines = 1

                onEditorAction { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        hideKeyboard(ctx)
                    }
                }
            }.lparams(matchParent, wrapContent) {
                below(R.id.taskDetailActivityEdtTitle)
                topMargin = dimen(R.dimen.newTaskTvDescriptionMarginTop)
            }

            rgStatus = radioGroup {
                lparams(matchParent, wrapContent)
                id = R.id.taskDetailActivityRdGroup

                radioButton {
                    id = R.id.taskDetailActivityRdDone
                }

                radioButton {
                    id = R.id.taskDetailActivityRdDoing
                }

            }.lparams {
                centerInParent()
                below(R.id.taskDetailActivityEdtDescription)
            }

            verticalLayout {
                lparams(matchParent, wrapContent)
                btnUpdate = button {
                    id = R.id.taskDetailActivityBtnUpdate
                    textResource = R.string.taskDetailBtnUpdate
                    isEnabled = false

                    onClick {
                        owner.eventOnViewClicked(it!!)
                        hideKeyboard(ctx)
                    }
                }.lparams {
                    topMargin = dimen(R.dimen.newTaskBtnSubmitMarginTop)

                }

                btnDelete = button {
                    id = R.id.taskDetailActivityBtnDelete
                    textResource = R.string.taskDetailBtnDelete
                    isEnabled = false

                    onClick {
                        owner.eventOnViewClicked(it!!)
                        hideKeyboard(ctx)
                    }
                }.lparams {
                    topMargin = dimen(R.dimen.newTaskBtnSubmitMarginTop)

                }
            }.lparams {
                below(R.id.taskDetailActivityRdGroup)
            }


            progressBar = progressBar {
                visibility = View.GONE
            }.lparams {
                centerInParent()
            }
        }.applyRecursively { view: View ->
            if (view is EditText) {
                when (view) {
                    edtTitle, edtDescription -> view.onTextChangeListener {
                        owner.onHandleTextChange(edtTitle.text.toString().trim(),
                                edtDescription.text.toString().trim())
                    }
                }
            }
        }
    }
}
