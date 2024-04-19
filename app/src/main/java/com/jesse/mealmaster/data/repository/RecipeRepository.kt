package com.jesse.mealmaster.data.repository

import android.graphics.Bitmap
import com.jesse.mealmaster.data.model.Recipe

interface RecipeRepository {

    suspend fun getRecipe(recipeImage: Bitmap): Result<Recipe>
}