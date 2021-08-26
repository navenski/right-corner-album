package com.example.feature_splash.viewmodel

import com.example.feature_splash.router.SplashRouter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class SplashViewModelTest{
     @Test
    fun test_navigation() {
         val mockRouter = mock<SplashRouter>()
        //Given Initialize SplashViewModel
         val viewModel = SplashViewModel()
         viewModel.bindRouter(mockRouter)

         //When triggerStart
         viewModel.triggerAppStart()

         //Then navigate and
         verify(mockRouter, times(1)).navigate()
         verify(mockRouter, times(1)).close()
    }
}