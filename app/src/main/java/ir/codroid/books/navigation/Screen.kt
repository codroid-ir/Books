package ir.codroid.books.navigation

import ir.codroid.books.common.Constants.BOOK_DETAIL_SCREEN_ROUTE
import ir.codroid.books.common.Constants.BOOK_LIST_SCREEN_ROUTE
import ir.codroid.books.common.Constants.SPLASH_SCREEN_ROUTE

sealed class Screen(val route : String) {
    data object Splash : Screen(SPLASH_SCREEN_ROUTE)
    data object BookList : Screen(BOOK_LIST_SCREEN_ROUTE)
    data object BookDetail : Screen(BOOK_DETAIL_SCREEN_ROUTE)
}