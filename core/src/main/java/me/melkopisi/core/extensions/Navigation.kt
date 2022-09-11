package me.melkopisi.core.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import me.melkopisi.core.BaseActivity
import me.melkopisi.core.Navigator

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
fun Fragment.navigateTo(navigator: Navigator, bundle: Bundle? = null) {
  (activity as BaseActivity).navigateTo(navigator, bundle)
}