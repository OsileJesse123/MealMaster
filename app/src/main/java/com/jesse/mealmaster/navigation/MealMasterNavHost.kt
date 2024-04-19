package com.jesse.mealmaster.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jesse.mealmaster.ui.screens.capture_recipe.CaptureRecipeRoute
import com.jesse.mealmaster.ui.screens.recipe_detail.DetailRoute
import com.jesse.mealmaster.ui.shared.SharedViewModel

@Composable
fun MealMasterNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(navController = navHostController, startDestination = CaptureRecipe.route){
        composable(CaptureRecipe.route){
            CaptureRecipeRoute(onNavigateToRecipeDetailsScreen = {
                    navHostController.navigate(RecipeDetails.route)
            },
                sharedViewModel = sharedViewModel
            )
        }
        composable(
            route = RecipeDetails.route
        ){
            DetailRoute(onNavigateBack = { navHostController.navigateUp()}, sharedViewModel = sharedViewModel)
        }
    }
}