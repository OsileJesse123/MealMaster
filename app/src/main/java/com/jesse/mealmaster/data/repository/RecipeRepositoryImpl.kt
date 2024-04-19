package com.jesse.mealmaster.data.repository

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.jesse.mealmaster.data.model.Recipe
import com.jesse.mealmaster.di.Dispatcher
import com.jesse.mealmaster.di.MealMasterDispatchers.IO
import com.jesse.mealmaster.util.prompt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    // TODO 004: Inject Generative AI Model
    private val generativeModel: GenerativeModel,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
): RecipeRepository {

    // TODO 005: implement getRecipe function
    override suspend fun getRecipe(recipeImage: Bitmap): Result<Recipe> {
        return withContext(ioDispatcher){
            try {
                val inputContent = content {
                    image(recipeImage)
                    text(prompt)
                }
                val response = generativeModel.generateContent(inputContent)
                Timber.e("Response: $response")
                Result.success(Recipe.toRecipe(response.text))
            } catch (e: Exception){
                Timber.e("Exception: $e")
                Result.failure(e)
            }
        }
    }
}
