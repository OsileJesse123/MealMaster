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
