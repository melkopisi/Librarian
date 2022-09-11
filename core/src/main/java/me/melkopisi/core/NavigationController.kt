package me.melkopisi.core

import android.os.Bundle

/*
 * Authored by Kopisi on 11 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
interface NavigationController {
  fun navigateTo(navigator: Navigator, bundle: Bundle? = null)
}

sealed class Navigator {
  object BookDetails : Navigator()
  object SearchBooks : Navigator()
}

object NavigationKeys {
  const val BOOK_DETAILS = "book_details"
  const val SEARCH_QUERY = "search_query"
}