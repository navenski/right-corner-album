package com.navektest.core_ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * TextView bindingAdapter
 */
object TextBindingAdapter {
    @JvmStatic
    @BindingAdapter("idToText")
    fun setId(
        textView: TextView, id: Long?
    ) {
        id?.let { textView.text = "$it" }
    }
}