package com.demo.marvel.shared.util

import android.util.Log

object Logger {
    private const val TAG = "-TAG"

    fun Any.logDebug(message: String) {
        Log.d("${this::class.simpleName}$TAG", message)
    }

    fun Any.logError(message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            Log.e("${this::class.simpleName}$TAG", message, throwable)
        } else {
            Log.e("${this::class.simpleName}$TAG", message)
        }
    }

    fun Any.logInfo(message: String) {
        Log.i("${this::class.simpleName}$TAG", message)
    }

    fun Any.logVerbose(message: String) {
        Log.v("${this::class.simpleName}$TAG", message)
    }

    fun Any.logWarning(message: String) {
        Log.w("${this::class.simpleName}$TAG", message)
    }
}