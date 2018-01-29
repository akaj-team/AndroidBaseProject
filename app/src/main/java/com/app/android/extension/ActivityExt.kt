package com.app.android.extension

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.app.android.ui.dialog.LoadingDialog

/**
 * Show LoadingDialog.
 */
fun AppCompatActivity.showLoadingDialog() {
    getLoadingDialog()
            ?: LoadingDialog().show(supportFragmentManager, LoadingDialog::class.java.name)
}

/**
 * Dismiss LoadingDialog.
 */
fun AppCompatActivity.dismissLoadingDialog() {
    getLoadingDialog()?.dismissAllowingStateLoss()
}

/**
 * Use to replace fragment
 *
 * @param fragment        fragment
 * @param containerViewId container view id
 * @param addToBackStack  add to back-stack or not
 */
fun AppCompatActivity.replaceFragment(fragment: Fragment, containerViewId: Int, addToBackStack: Boolean = false) {
    addOrReplaceFragment(fragment, containerViewId, addToBackStack, true)
}

/**
 * Use to add fragment
 *
 * @param fragment        fragment
 * @param containerViewId container view id
 * @param addToBackStack  add to back-stack or not
 */
fun AppCompatActivity.addFragment(fragment: Fragment, containerViewId: Int, addToBackStack: Boolean) {
    addOrReplaceFragment(fragment, containerViewId, addToBackStack, false)
}

/**
 * Hide keyboard
 */
fun AppCompatActivity.hideKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.run {
        hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}

private fun AppCompatActivity.addOrReplaceFragment(fragment: Fragment, containerViewId: Int, addToBackStack: Boolean, isReplace: Boolean) {
    val transaction = supportFragmentManager.beginTransaction()
    if (addToBackStack) {
        transaction.addToBackStack(fragment.javaClass.name)
    }
    if (isReplace) {
        transaction.replace(containerViewId, fragment, fragment.javaClass.name)
    } else {
        transaction.add(containerViewId, fragment, fragment.javaClass.name)
    }
    transaction.commit()
}

private fun AppCompatActivity.getLoadingDialog(): LoadingDialog? {
    return supportFragmentManager.findFragmentByTag(LoadingDialog::class.java.name) as? LoadingDialog
}

/**
 * TODO: Show msg debug, It will be deleted in the future
 */
fun AppCompatActivity.toast(throwable: Throwable) {
    toast(throwable.message ?: "Unknown error")
}

/**
 * TODO: Show msg debug, It will be deleted in the future
 */
fun AppCompatActivity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}
