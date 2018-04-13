package com.app.android.ui.task.newtask

import android.graphics.Color
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
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
class NewTaskActivityUI : AnkoComponent<NewTaskActivity> {

    internal lateinit var progressBar: ProgressBar
    internal lateinit var edtTitle: EditText
    internal lateinit var edtDescription: EditText
    internal lateinit var btnSubmit: Button

    override fun createView(ui: AnkoContext<NewTaskActivity>) = with(ui) {
        relativeLayout {
            lparams(matchParent, matchParent)
            backgroundColor = Color.WHITE
            isFocusable = true
            isFocusableInTouchMode = true
            padding = dimen(R.dimen.newTaskRlPadding)

            textView(R.string.newTaskTile) {
                id = R.id.newTaskActivityTvTitle
                textSizeDimen = R.dimen.newTaskTvTitleTextSize
                textColor = Color.BLACK
            }.lparams {
                centerHorizontally()
                topMargin = dimen(R.dimen.newTaskTitleMarginTop)
            }

            edtTitle = editText {
                id = R.id.newTaskActivityEdtTitle
                hintResource = R.string.newTaskHintTitle
                inputType = InputType.TYPE_CLASS_TEXT
                textSizeDimen = R.dimen.newTaskTvTitleTextSize
                maxLines = 1
            }.lparams(matchParent, wrapContent) {
                topMargin = dimen(R.dimen.newTaskTvTitleMarginTop)
            }

            edtDescription = editText {
                id = R.id.newTaskActivityEdtDescription
                hintResource = R.string.newTaskHintDescription
                inputType = InputType.TYPE_CLASS_TEXT
                textSizeDimen = R.dimen.newTaskTvDescriptionTextSize
                maxLines = 1
            }.lparams(matchParent, wrapContent) {
                below(R.id.newTaskActivityEdtTitle)
                topMargin = dimen(R.dimen.newTaskTvDescriptionMarginTop)
            }

            btnSubmit = button(R.string.newTaskBtnSubmit) {
                id = R.id.newTaskActivityBtnSubmit
                isEnabled = false

                onClick {
                    owner.eventOnSubmitClicked()
                    hideKeyboard(ctx)
                }
            }.lparams {
                centerInParent()
                below(R.id.newTaskActivityEdtDescription)
                topMargin = dimen(R.dimen.newTaskBtnSubmitMarginTop)

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
