package com.example.android.lesson8

import android.app.Application

class MyApplication: Application() {
    val dependencyContainer = DependencyContainer(this)
}