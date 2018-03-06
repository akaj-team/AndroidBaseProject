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
            isFocusable = true
            isFocusableInTouchMode = true
            padding = dimen(R.dimen.taskDetailRlPadding)

            textView(R.string.taskDetailTitle) {
                id = R.id.taskDetailActivityTvTitle
                textSize = px2dip(dimen(R.dimen.taskDetailTvTitleTextSize))
                textColor = Color.BLACK
            }.lparams {
                centerHorizontally()
                topMargin = dimen(R.dimen.taskDetailTitleMarginTop)
            }

            edtTitle = editText {
                id = R.id.taskDetailActivityEdtTitle
                hintResource = R.string.taskDetailHintTitle
                inputType = InputType.TYPE_CLASS_TEXT
                textSize = px2dip(dimen(R.dimen.taskDetailTvTitleTextSize))
                maxLines = 1
            }.lparams(matchParent, wrapContent) {
                topMargin = dimen(R.dimen.taskDetailTvTitleMarginTop)
            }

            edtDescription = editText {
                id = R.id.taskDetailActivityEdtDescription
                hintResource = R.string.taskDetailHintDescription
                inputType = InputType.TYPE_CLASS_TEXT
                textSize = px2dip(dimen(R.dimen.taskDetailTvDescriptionTextSize))
                maxLines = 1
            }.lparams(matchParent, wrapContent) {
                below(R.id.taskDetailActivityEdtTitle)
                topMargin = dimen(R.dimen.taskDetailTvDescriptionMarginTop)
            }

            rgStatus = radioGroup {
                lparams(matchParent, wrapContent)
                orientation = RadioGroup.HORIZONTAL
                id = R.id.taskDetailActivityRdGroup

                radioButton {
                    id = R.id.taskDetailActivityRdDone
                    textResource = R.string.taskDone
                }

                radioButton {
                    id = R.id.taskDetailActivityRdDoing
                    textResource = R.string.taskDoing
                }

            }.lparams {
                centerInParent()
                below(R.id.taskDetailActivityEdtDescription)
            }

            linearLayout {
                lparams(matchParent, wrapContent)
                btnUpdate = button(R.string.taskDetailBtnUpdate) {
                    id = R.id.taskDetailActivityBtnUpdate
                    isEnabled = false

                    onClick {
                        owner.eventOnUpdateClicked(it!!)
                        hideKeyboard(ctx)
                    }
                }.lparams {
                    topMargin = dimen(R.dimen.taskDetailBtnUpdateMarginTop)

                }

                btnDelete = button(R.string.taskDetailBtnDelete) {
                    id = R.id.taskDetailActivityBtnDelete

                    onClick {
                        owner.eventOnDeleteClicked(it!!)
                        hideKeyboard(ctx)
                    }
                }.lparams {
                    topMargin = dimen(R.dimen.taskDetailBtnUpdateMarginTop)

                }
            }.lparams {
                below(R.id.taskDetailActivityRdGroup)
                centerInParent()
            }


            progressBar = progressBar {
                visibility = View.GONE
            }.lparams {
                centerInParent()
            }
        }.applyRecursively { view: View ->
            if (view is EditText) {
                when (view) {
                    edtTitle, edtDescription -> {
                        view.run {
                            onTextChangeListener {
                                owner.onHandleTextChange(edtTitle.text.toString().trim(),
                                        edtDescription.text.toString().trim())
                            }
                            onEditorAction { _, actionId, _ ->
                                if (actionId == EditorInfo.IME_ACTION_DONE) {
                                    hideKeyboard(ctx)
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
