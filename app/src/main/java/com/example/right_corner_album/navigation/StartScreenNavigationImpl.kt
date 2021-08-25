package com.example.right_corner_album.navigation

import android.content.Context
import android.content.Intent
import com.example.right_corner_album.GlideActivity
import com.example.right_corner_album.MainActivity
import com.navektest.core_navigation.startScreen.StartScreenNavigation

class StartScreenNavigationImpl : StartScreenNavigation {
    override fun navigate(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
//        val intent = Intent(context, GlideActivity::class.java)
//        context.startActivity(intent)
    }
}