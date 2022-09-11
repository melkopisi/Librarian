package me.melkopisi.core.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
fun Fragment.hideKeyboard() {
  val imm: InputMethodManager =
    activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
  //Find the currently focused view, so we can grab the correct window token from it.
  //Find the currently focused view, so we can grab the correct window token from it.
  var view = activity!!.currentFocus
  //If no view currently has focus, create a new one, just so we can grab a window token from it
  //If no view currently has focus, create a new one, just so we can grab a window token from it
  if (view == null) {
    view = View(activity)
  }
  imm.hideSoftInputFromWindow(view.windowToken, 0)
}