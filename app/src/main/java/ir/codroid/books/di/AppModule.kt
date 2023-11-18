package ir.codroid.books.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.codroid.books.data.remote.BookApi
import ir.codroid.books.data.repository.BookRepositoryImpl
import ir.codroid.books.domin.repository.BookRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookRepository(api: BookApi): BookRepository = BookRepositoryImpl(api)
}