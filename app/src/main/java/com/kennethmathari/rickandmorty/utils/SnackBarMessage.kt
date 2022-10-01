package com.kennethmathari.rickandmorty.utils

import android.app.Activity
import com.google.android.material.snackbar.Snackbar

/**
 * Extension function for displaying a snackbar in an Activity.
 */
fun Activity.showSnackBar(message: String) {
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
}