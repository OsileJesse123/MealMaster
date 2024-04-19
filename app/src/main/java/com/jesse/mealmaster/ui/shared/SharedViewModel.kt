package com.jesse.mealmaster.ui.shared

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jesse.mealmaster.data.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel(){

    var recipe = mutableStateOf<Recipe?>(null)
        private set

    fun updateRecipe(recipeValue: Recipe){
        recipe.value = recipeValue
    }
}