package com.jesse.mealmaster.data.repository

import com.jesse.mealmaster.di.Dispatcher
import com.jesse.mealmaster.di.MealMasterDispatchers.IO
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    // TODO 004: Inject Generative AI Model

    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
): RecipeRepository {

    // TODO 005: implement getRecipe function
}

// 005: Code
//override suspend fun getRecipe(recipeImage: Bitmap): Result<Recipe> {
//    return withContext(ioDispatcher){
//        try {
//            val inputContent = content {
//                image(recipeImage)
//                text(prompt)
//            }
//            val response = generativeModel.generateContent(inputContent)
//            Timber.e("Response: $response")
//            Result.success(Recipe.toRecipe(response.text))
//        } catch (e: Exception){
//            Timber.e("Exception: $e")
//            Result.failure(e)
//        }
//    }
//}
