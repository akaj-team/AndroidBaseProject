package com.app.android.extension

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast

/**
 * Copyright © 2017 AsianTech inc.
 * Extension for Activity
 */

/**
 * Toast message
 * @param message: CharSequence to show on toast
 * @param duration: Int, default is Toast.LENGTH_SHORT
 */
internal fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

/**
 * Extension method to inflate layout for ViewGroup.
 */
internal fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View
        = LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

internal fun FragmentActivity.replaceFragment(@IdRes containerId: Int, fragment: Fragment) {
    this.supportFragmentManager.beginTransaction().replace(containerId, fragment).commit()
}

internal fun FragmentActivity.addFragment(@IdRes containerId: Int, fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.addToBackStack(fragment.javaClass.simpleName)
    transaction.add(containerId, fragment)
    transaction.commit()
}

internal fun Context.getWidthScreen(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as? WindowManager
    val dimension = DisplayMetrics()
    wm?.defaultDisplay?.getMetrics(dimension)
    return dimension.widthPixels
}

internal fun Context.getHeightScreen(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as? WindowManager
    val dimension = DisplayMetrics()
    wm?.defaultDisplay?.getMetrics(dimension)
    return dimension.heightPixels
}

internal fun Context.getNavigationBarHeight(): Int {
    val id = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    if (id > 0) {
        return resources.getDimensionPixelSize(id)
    }
    return 0
}
