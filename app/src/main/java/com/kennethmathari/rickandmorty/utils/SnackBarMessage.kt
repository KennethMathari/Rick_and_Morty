package com.kennethmathari.rickandmorty.utils

import android.app.Activity
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnackBar(message: String) {
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
}