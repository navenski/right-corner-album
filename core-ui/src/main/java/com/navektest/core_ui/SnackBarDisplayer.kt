package com.navektest.core_ui

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.core_ui.R
import com.google.android.material.snackbar.Snackbar
import java.lang.ref.WeakReference

enum class SnackBarMessageType {
    SUCCESS,
    ERROR
}

enum class SnackBarDurationType(val duration: Int) {
    SHORT(Snackbar.LENGTH_SHORT),
    LONG(Snackbar.LENGTH_LONG),
    INDEFINITE(Snackbar.LENGTH_INDEFINITE)
}


class SnackBarDisplayer(activity: Activity) {

    private val weakActivity = WeakReference(activity)

    fun display(
        message: String,
        snackMessageType: SnackBarMessageType,
        snackLength: SnackBarDurationType = SnackBarDurationType.SHORT,
        actionName: String? = null,
        actionColorRes: Int? = null,
        onActionClick: View.OnClickListener? = null,
        maxLines:Int = 5
    ) {

        weakActivity.get()?.let {
            val view = it.window.decorView.findViewById<View>(android.R.id.content)
            val snackBar = Snackbar.make(view, message, snackLength.duration)
            val snackBarRootView = snackBar.view
            val snackBarTextView = snackBarRootView.findViewById<TextView>(R.id.snackbar_text)

            val backgroundColorResId = when (snackMessageType) {
                SnackBarMessageType.SUCCESS -> R.color.accent_green
                SnackBarMessageType.ERROR -> R.color.salmon
            }

            val backgroundColor = ContextCompat.getColor(it, backgroundColorResId)
            snackBarRootView.setBackgroundColor(backgroundColor)
            snackBarTextView.setTextColor(ContextCompat.getColor(it, R.color.white))
            snackBarTextView.maxLines = maxLines
            snackBarRootView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            snackBar.setAction(actionName, onActionClick)

            if (actionColorRes != null)
                snackBar.setActionTextColor(ContextCompat.getColor(it, actionColorRes))

            snackBar.show()
        }
    }
}