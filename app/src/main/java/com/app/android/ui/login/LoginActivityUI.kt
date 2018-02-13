package com.app.android.ui.login

import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.app.android.R
import com.app.android.extension.hideKeyboard
import com.app.android.extension.onTextChangeListener
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/9/18.
 */
class LoginActivityUI : AnkoComponent<LoginActivity> {

    internal lateinit var tvNext: TextView
    internal lateinit var progressBar: ProgressBar
    internal lateinit var edtUsername: EditText
    internal lateinit var edtPassword: EditText

    override fun createView(ui: AnkoContext<LoginActivity>) = with(ui) {
        relativeLayout {
            lparams(matchParent, matchParent)
            backgroundColor = ContextCompat.getColor(ctx, android.R.color.white)

            tvNext = textView(R.string.login_activity_tv_next) {
                id = R.id.login_activity_tv_next
                textColor = ContextCompat.getColor(ctx, R.color.red_blur)
                textSize = px2dip(dimen(R.dimen.login_tv_next_text_size))
                isEnabled = false

                onClick {
                    owner.onClickListener(it!!)
                    hideKeyboard(ctx)
                }
            }.lparams {
                alignParentRight()
                padding = dimen(R.dimen.login_tv_next_padding)
            }

            textView(R.string.login_activity_tv_sign_in) {
                id = R.id.login_activity_tv_sign_in
                textColor = ContextCompat.getColor(ctx, android.R.color.black)
                textSize = px2dip(dimen(R.dimen.login_tv_sign_in_text_size))
            }.lparams {
                centerHorizontally()
                topMargin = dimen(R.dimen.login_tv_sign_in_margin_top)
            }

            textView(R.string.login_activity_tv_sub_message) {
                id = R.id.login_activity_tv_sub_message
                textColor = ContextCompat.getColor(ctx, android.R.color.darker_gray)
                textSize = px2dip(dimen(R.dimen.login_tv_sub_message_text_size))
            }.lparams {
                centerHorizontally()
                below(R.id.login_activity_tv_sign_in)
                topMargin = dimen(R.dimen.login_tv_sub_message_margin_top)
            }

            edtUsername = editText {
                id = R.id.login_activity_edt_username
                hint = resources.getString(R.string.login_activity_edt_username_hint)
                hintTextColor = ContextCompat.getColor(ctx, android.R.color.darker_gray)
                maxLines = 1
                inputType = InputType.TYPE_CLASS_TEXT
                textSize = px2dip(dimen(R.dimen.login_edt_input_text_size))
                textColor = ContextCompat.getColor(ctx, android.R.color.holo_red_light)
            }.lparams(matchParent, wrapContent) {
                centerHorizontally()
                margin = dimen(R.dimen.login_layout_margin)
                below(R.id.login_activity_tv_sub_message)
                topMargin = dimen(R.dimen.login_edt_username_margin_top)
            }

            edtPassword = editText {
                id = R.id.login_activity_edt_password
                hint = resources.getString(R.string.login_activity_edt_password_hint)
                hintTextColor = ContextCompat.getColor(ctx, android.R.color.darker_gray)
                maxLines = 1
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                textSize = px2dip(dimen(R.dimen.login_edt_input_text_size))
                textColor = ContextCompat.getColor(ctx, android.R.color.holo_red_light)
            }.lparams(matchParent, wrapContent) {
                centerHorizontally()
                margin = dimen(R.dimen.login_layout_margin)
                below(R.id.login_activity_edt_username)
                topMargin = dimen(R.dimen.login_edt_password_margin_top)
            }

            textView(R.string.login_activity_tv_forgot_password) {
                id = R.id.login_activity_tv_forgot_password
                textSize = px2dip(dimen(R.dimen.login_tv_forgot_password_text_size))
                textColor = ContextCompat.getColor(ctx, android.R.color.holo_red_light)
            }.lparams {
                alignParentBottom()
                margin = dimen(R.dimen.login_layout_margin)
            }

            progressBar = progressBar {
                visibility = View.GONE
            }.lparams {
                centerInParent()
            }
        }.applyRecursively { view: View ->
            if (view is EditText) {
                when (view) {
                    edtUsername, edtPassword -> view.onTextChangeListener {
                        owner.onTextChangeListener(edtUsername.text.toString().trim(),
                                edtPassword.text.toString().trim())
                    }
                }
            }
        }
    }
}
