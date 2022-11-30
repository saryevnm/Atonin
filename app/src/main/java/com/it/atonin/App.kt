package com.it.atonin

import android.app.Application
import android.util.Log
import com.it.atonin.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(AppModule))
        }
        ExceptionHandler.setupExceptionHandler()
    }
}

object ExceptionHandler {
    fun setupExceptionHandler() {
        val defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            val message = "Uncaught exception in thread ${thread.name}:\n"
            Log.e("AndroidRuntime", message, throwable)
            defaultUncaughtExceptionHandler?.uncaughtException(thread, throwable)
        }
    }
}