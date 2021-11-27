package com.example.nopyjf.nopyjfmobilelistmvvm

import android.app.Application
import com.example.nopyjf.nopyjfmobilelistmvvm.di.moduleList
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(moduleList)
        }
    }
}