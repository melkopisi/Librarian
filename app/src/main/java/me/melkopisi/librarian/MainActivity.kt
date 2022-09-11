package me.melkopisi.librarian

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import me.melkopisi.core.BaseActivity
import me.melkopisi.core.NavigationController
import me.melkopisi.core.Navigator
import me.melkopisi.core.Navigator.BookDetails
import me.melkopisi.core.Navigator.SearchBooks

@AndroidEntryPoint
class MainActivity : BaseActivity(), NavigationController {
  private lateinit var navHostFragment: NavHostFragment
  private lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    navController = navHostFragment.navController
  }

  override fun navigateTo(navigator: Navigator, bundle: Bundle?) {
    when (navigator) {
      is BookDetails -> navController.navigate(R.id.bookDetailsFragment, bundle)
      is SearchBooks -> navController.navigate(R.id.booksSearchFragment, bundle)
    }
  }
}