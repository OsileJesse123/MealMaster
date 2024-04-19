package com.jesse.mealmaster.di

import com.jesse.mealmaster.data.repository.RecipeRepository
import com.jesse.mealmaster.data.repository.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repoImpl: RecipeRepositoryImpl): RecipeRepository
}