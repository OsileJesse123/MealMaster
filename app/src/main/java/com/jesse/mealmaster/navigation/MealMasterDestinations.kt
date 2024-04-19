package com.jesse.mealmaster.navigation

interface MealMasterDestinations {
    val route: String
}

object CaptureRecipe: MealMasterDestinations{
    override val route: String = "/capture_recipe"
}

object RecipeDetails: MealMasterDestinations{
    override val route: String = "/recipe_details"
}