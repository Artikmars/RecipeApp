package com.artamonov.recipeapp.di

import android.content.Context
import com.artamonov.recipeapp.allrecipes.database.RecipeDao
import com.artamonov.recipeapp.allrecipes.database.RecipeRepository
import com.artamonov.recipeapp.allrecipes.database.RecipeRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
class RoomDatabaseModule {

    @Provides
    fun provideRecipeDao(@ApplicationContext appContext: Context): RecipeDao {
        return RecipeRoomDatabase.getDatabase(appContext).recipeDao()
    }

    @Provides
    fun provideRecipeBRepository(recipeDao: RecipeDao) = RecipeRepository(recipeDao)

}
