package me.melkopisi.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.melkopisi.data.local.datasource.BooksLocalDataSource
import me.melkopisi.data.local.datasource.BooksLocalDataSourceImpl
import me.melkopisi.data.remote.datasource.BooksRemoteDataSource
import me.melkopisi.data.remote.datasource.BooksRemoteDataSourceImpl

/*
 * Authored by Kopisi on 10 Sep, 2022.
 * Contact Me : m.elkopisi@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
  @Binds
  abstract fun bindBookRemoteDataSource(booksRemoteDataSourceImpl: BooksRemoteDataSourceImpl): BooksRemoteDataSource

  @Binds
  abstract fun bindBookLocalDataSource(booksLocalDataSourceImpl: BooksLocalDataSourceImpl): BooksLocalDataSource
}