package com.jesse.mealmaster.ui.screens.capture_recipe

import android.graphics.Bitmap
import com.jesse.mealmaster.data.model.Recipe

data class CaptureRecipeUiState(
    val bitmap: Bitmap? = null,
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    /**
     * This is a string resource id
     * */
    val errorMessage: Int? = null,
    /**
    * Signifies that a recipe was successfully identified alongside it's ingredients and steps
    * */
    val successful: Boolean = false
)
