package me.melkopisi.core.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */

fun View.makeSnackBar(msg: String, duration: Int = Snackbar.LENGTH_SHORT) {
  Snackbar.make(this, msg, duration).show()
}