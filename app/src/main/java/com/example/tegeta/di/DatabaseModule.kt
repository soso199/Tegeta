package com.example.tegeta.di

import android.content.Context
import com.example.tegeta.data.AppDatabase
import com.example.tegeta.data.dao.CurrentCarsDao
import com.example.tegeta.data.dao.ServicesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideCurrentCarsDao(appDatabase: AppDatabase): CurrentCarsDao {
        return appDatabase.currentCarsDao()
    }

    @Provides
    fun provideServicesDao(appDatabase: AppDatabase): ServicesDao {
        return appDatabase.servicesDao()
    }
}
