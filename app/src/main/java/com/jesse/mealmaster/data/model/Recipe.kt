package com.jesse.mealmaster.data.model

import com.google.gson.Gson
import com.jesse.mealmaster.util.RecipeStatus

data class Recipe(
    val name: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val status: Int
){
    // Returns an Enum based on the status
    fun getStatus(): RecipeStatus{
        return when(status){
            0 -> RecipeStatus.RECIPE_NOT_IDENTIFIED
            1 -> RecipeStatus.RECIPE_IDENTIFIED
            2 -> RecipeStatus.RECIPE_IDENTIFIED_NO_STEPS_OR_INGREDIENTS
            else -> RecipeStatus.NO_STATUS
        }
    }

    companion object {
        fun toRecipe(jsonFormat: String?): Recipe = Gson().fromJson(jsonFormat, Recipe::class.java)
    }
}
