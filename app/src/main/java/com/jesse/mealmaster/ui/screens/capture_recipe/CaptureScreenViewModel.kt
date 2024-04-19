package com.jesse.mealmaster.ui.screens.capture_recipe

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesse.mealmaster.R
import com.jesse.mealmaster.data.repository.RecipeRepository
import com.jesse.mealmaster.util.RecipeStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaptureScreenViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
): ViewModel(){

    private val _captureScreenUiState: MutableStateFlow<CaptureRecipeUiState> = MutableStateFlow(
        CaptureRecipeUiState()
    )
    val captureScreenUiState get() = _captureScreenUiState.asStateFlow()

    // TODO 006: implement the identifyRecipe function
    fun identifyRecipe(recipeImage: Bitmap){
        viewModelScope.launch {
            // Update the image in the UI and set UI state to loading
            _captureScreenUiState.update {
                captureScreenUiState ->
                captureScreenUiState.copy(bitmap = recipeImage, isLoading = true)
            }
            // Try to get recipe from image
            recipeRepository.getRecipe(recipeImage)
                .onSuccess {
                    recipe ->
                    _captureScreenUiState.update {
                            captureScreenUiState ->
                        captureScreenUiState.copy(
                            isLoading = false,
                            recipe = recipe,
                            successful = when(recipe.getStatus()){
                                RecipeStatus.RECIPE_IDENTIFIED -> true
                                else -> false
                            },
                            errorMessage = when(recipe.getStatus()){
                                RecipeStatus.RECIPE_IDENTIFIED_NO_STEPS_OR_INGREDIENTS -> R.string.cannot_cook_meal
                                RecipeStatus.RECIPE_NOT_IDENTIFIED -> R.string.could_not_identify_a_meal
                                else -> null
                            }
                        )
                    }
                }
                .onFailure {
                    _captureScreenUiState.update {
                            captureScreenUiState ->
                        captureScreenUiState.copy(isLoading = false, errorMessage = R.string.failed_to_perform_identification)
                    }
                }
        }
    }

    fun onErrorMessageShown(){
        _captureScreenUiState.update {
                captureScreenUiState ->
                captureScreenUiState.copy(errorMessage = null)
        }
    }
}