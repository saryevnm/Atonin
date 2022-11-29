package com.it.atonin.utils

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.view.Gravity
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.it.atonin.R


inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
    when (T::class) {
        Boolean::class -> return this.getBoolean(key, defaultValue as Boolean) as T
        Float::class -> return this.getFloat(key, defaultValue as Float) as T
        Int::class -> return this.getInt(key, defaultValue as Int) as T
        Long::class -> return this.getLong(key, defaultValue as Long) as T
        String::class -> return this.getString(key, defaultValue as String) as T
        else -> {
            if (defaultValue is Set<*>) {
                return this.getStringSet(key, defaultValue as Set<String>) as T
            }
        }
    }

    return defaultValue
}

inline fun <reified T> SharedPreferences.put(key: String, value: T) {
    val editor = this.edit()

    when (T::class) {
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        Float::class -> editor.putFloat(key, value as Float)
        Int::class -> editor.putInt(key, value as Int)
        Long::class -> editor.putLong(key, value as Long)
        String::class -> editor.putString(key, value as String)
        else -> {
            if (value is Set<*>) {
                editor.putStringSet(key, value as Set<String>)
            }
        }
    }
    editor.apply()
}

fun Fragment.getFragmentNavController(@IdRes id: Int) = activity?.let {
    return@let Navigation.findNavController(it, id)
}

@SuppressLint("RtlHardcoded")
fun DrawerLayout.hide() {
    if (isDrawerOpen(Gravity.RIGHT)) closeDrawer(
        Gravity.RIGHT
    )
}

@SuppressLint("RtlHardcoded")
fun DrawerLayout.show() {
    if (!isDrawerOpen(Gravity.RIGHT)) openDrawer(
        Gravity.RIGHT
    )
}

fun ImageView.setImage(url: String) {
    Glide.with(context)
        .load(url)
        .thumbnail(0.2F)
        .centerCrop()
        .placeholder(R.drawable.placeholder)
        .into(this)
}
