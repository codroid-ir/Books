package ir.codroid.books.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.codroid.books.presentation.book_detail.BookDetailRoute
import ir.codroid.books.presentation.book_list.BookListRoute

@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.BookList.route) {
        composable(route = Screen.Splash.route) {

        }
        composable(route = Screen.BookList.route) {
            BookListRoute(
                navController = navController
            )
        }
        composable(route = Screen.BookDetail.route + "?bookId={bookId}", arguments = listOf(
            navArgument("bookId") {
                type = NavType.StringType
                defaultValue = ""
            }
        )) { navBackStack ->
            navBackStack.arguments?.getString("bookId")?.let { bookId ->
                BookDetailRoute(
                    navController = navController,
                    bookId = bookId
                )
            }
        }
    }
}