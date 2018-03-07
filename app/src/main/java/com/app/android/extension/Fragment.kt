package com.app.android.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.app.android.ui.base.BaseFragment

/**
 * Use this extension for Fragment, FragmentTransaction
 * @author at-tienhoang
 */

internal fun Fragment.addChildFragment(@IdRes containerId: Int, fragment: BaseFragment, backStack: String? = null,
                                       t: (transaction: FragmentTransaction) -> Unit = {}) {
    if (childFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) == null) {
        val transaction = childFragmentManager.beginTransaction()
        t.invoke(transaction)
        transaction.add(containerId, fragment, fragment.javaClass.simpleName)
        if (backStack != null) {
            transaction.addToBackStack(backStack)
        }
        transaction.commit()
    }
}

internal fun Fragment.replaceChildFragment(@IdRes containerId: Int, fragment: BaseFragment, backStack: String? = null,
                                           t: (transaction: FragmentTransaction) -> Unit = {}) {
    val transaction = childFragmentManager.beginTransaction()
    t.invoke(transaction)
    transaction.replace(containerId, fragment, backStack)
    if (backStack != null) {
        transaction.addToBackStack(backStack)
    }
    transaction.commit()
}

internal fun Fragment.getCurrentFragment(@IdRes containerId: Int)
        = childFragmentManager.findFragmentById(containerId)

internal fun FragmentTransaction.animFadeInFadeOut() {
    // TODO Set custom animation
}

internal fun FragmentTransaction.animSlideInRightSlideOutRight() {
    // TODO Set custom animation
}
