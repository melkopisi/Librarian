package me.melkopisi.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import me.melkopisi.core.R

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
private fun String.toCoverUrl() = "https://covers.openlibrary.org/b/id/$this.jpg"
fun ImageView.loadImage(coverId: String?) {
  Glide.with(this).load(coverId?.toCoverUrl()).placeholder(R.drawable.ic_book_placeholder).error(R.drawable.ic_book_placeholder).into(this)
}