package me.melkopisi.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
private fun String.toCoverUrl() = "https://covers.openlibrary.org/b/id/$this.jpg"
fun ImageView.loadImage(coverId: String) {
  Glide.with(this).load(coverId.toCoverUrl()).into(this)
}