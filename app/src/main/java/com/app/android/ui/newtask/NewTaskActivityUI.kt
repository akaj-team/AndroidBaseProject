package com.app.android.ui.newtask

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
            padding = dimen(R.dimen.newTaskRlPadding)

            edtTitle = editText {
                id = R.id.newTaskActivityEdtTitle
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
                id = R.id.newTaskActivityEdtDescription
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
                below(R.id.newTaskActivityEdtTitle)
                topMargin = dimen(R.dimen.newTaskTvDescriptionMarginTop)
            }

            btnSubmit = button {
                id = R.id.newTaskActivityBtnSubmit
                textResource = R.string.newTaskBtnSubmit

                onClick {
                    owner.eventOnViewClicked(it!!)
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
                    edtTitle, edtDescription -> view.onTextChangeListener {
                        owner.onHandleTextChange(edtTitle.text.toString().trim(),
                                edtDescription.text.toString().trim())
                    }
                }
            }
        }
    }
}
