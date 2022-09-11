package me.melkopisi.core

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
abstract class BaseActivity : AppCompatActivity(), NavigationController {
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      android.R.id.home -> {
        onBackPressed()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }
}