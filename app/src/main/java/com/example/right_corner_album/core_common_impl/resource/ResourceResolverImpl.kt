package com.example.right_corner_album.core_common_impl.resource

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.BoolRes
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import com.navektest.core_common.resource.ResourceResolver

class ResourceResolverImpl(private val context: Context) : ResourceResolver {

    override fun getBoolean(@BoolRes boolId: Int): Boolean {
        return context.resources.getBoolean(boolId)
    }

    override fun getString(@StringRes stringId: Int): String {
        return context.resources.getString(stringId)
    }

    override fun getString(@StringRes stringId: Int, vararg args: Any?): String {
        return String.format(context.resources.getString(stringId), *args)
    }

    override fun getPlural(@PluralsRes resId: Int, quantity: Int): String {
        return context.resources.getQuantityString(resId, quantity)
    }

    override fun getDimens(@DimenRes resId: Int): Float {
        return context.resources.getDimension(resId)
    }

    override fun getStringArray(@ArrayRes resId: Int): Array<String> {
        return context.resources.getStringArray(resId)
    }

    override fun getIdentifier(name: String, defType: String): Int {
        return context.resources.getIdentifier(name, defType, context.packageName)
    }

    override fun getInteger(@IntegerRes resId: Int): Int {
        return context.resources.getInteger(resId)
    }
}