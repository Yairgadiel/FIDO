package com.gy.whatsnew

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WNApplication : Application() {
    private val backFromBackgroundListeners = mutableListOf<AppBackFromBackgroundListener>()

    private var isInBackground = false

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_PAUSE) {
                    isInBackground = true
                }
                else if (event == Lifecycle.Event.ON_RESUME) {
                    if (isInBackground) {
                        isInBackground = false

                        backFromBackgroundListeners.forEach {
                            it.onBackFromBackground()
                        }
                    }
                }
            }
        })
    }

    fun addBackFromBackgroundListener(listener: AppBackFromBackgroundListener) {
        backFromBackgroundListeners.add(listener)
    }

    fun removeBackFromBackgroundListener(listener: AppBackFromBackgroundListener) {
        backFromBackgroundListeners.remove(listener)
    }
}