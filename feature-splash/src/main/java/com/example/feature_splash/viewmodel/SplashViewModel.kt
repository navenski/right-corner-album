package com.example.feature_splash.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_splash.router.SplashRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class SplashViewModel : ViewModel() {
    private var routerWeakRef: WeakReference<SplashRouter> = WeakReference<SplashRouter>(null)

    fun bindRouter(router: SplashRouter) {
        routerWeakRef = WeakReference(router)
    }

    fun triggerAppStart() {
        routerWeakRef.get()?.navigate()
        routerWeakRef.get()?.close()
    }
}