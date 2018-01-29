package com.app.android.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import com.app.android.R

class LoadingDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return inflater?.inflate(R.layout.dialog_loading, container, false)
    }

    override fun show(manager: FragmentManager?, tag: String?) {
        if (!isAdded) {
            super.show(manager, tag)
        }
    }
}
