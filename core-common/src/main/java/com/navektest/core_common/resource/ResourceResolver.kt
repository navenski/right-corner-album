package com.navektest.core_common.resource

import androidx.annotation.ArrayRes
import androidx.annotation.BoolRes
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

/**
 * Resolve resource
 */
interface ResourceResolver {
    fun getBoolean(@BoolRes boolId: Int): Boolean
    fun getString(@StringRes stringId: Int): String
    fun getString(@StringRes stringId: Int, vararg args: Any?): String
    fun getPlural(@PluralsRes resId: Int, quantity: Int): String
    fun getDimens(@DimenRes resId: Int): Float
    fun getStringArray(@ArrayRes resId: Int): Array<String>
    fun getIdentifier(name: String, defType: String): Int
    fun getInteger(@IntegerRes resId: Int): Int
}