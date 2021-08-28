package com.example.right_corner_album.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.right_corner_album.GlideActivity
import com.example.right_corner_album.MainActivity
import com.navektest.core_navigation.startScreen.StartScreenNavigation

class StartScreenNavigationImpl(private val context: Activity) : StartScreenNavigation {
    override fun navigate() {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }
}