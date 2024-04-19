package com.jesse.mealmaster.di

import com.google.ai.client.generativeai.GenerativeModel
import com.jesse.mealmaster.BuildConfig
import com.jesse.mealmaster.util.modelName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AIModelModule {

    // TODO 003: Define how to create Generative AI Model
    @Provides
    @Singleton
    fun providesAIModel() = GenerativeModel(
        modelName,
        BuildConfig.API_KEY
    )
}