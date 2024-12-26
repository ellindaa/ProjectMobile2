package id.ellinda.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(){
    val navController = rememberNavController()

    // TODO: menentukan host navigasi dg rute awal Banner Screen
    NavHost(navController = navController, startDestination = "Banner screen") {
        // TODO: menambahkan rute untuk layar Banner Screen
        composable("Banner screen") {
            BannerScreen(navController= navController)
        }
        // TODO: menambahkan rute untuk layar Home Screen
        composable("Home screen"){
            HomeScreen(navController= navController)
        }
        // TODO: menambahkan rute untuk layar Details Screen
        composable("Details screen/{id}",
            arguments = listOf(
                navArgument(
                    name = "id" 
                ) {
                    type = NavType.IntType
                }
            )
        ) {id->
            // TODO: mendapatkan nilai id dan memanggil detail screen
            id.arguments?.getInt("id")?.let { id1->
                DetailsScreen(id =id1) // TODO: menampilkan layar detail dengan id yang sesuai
            }

        }
    }
}
