package com.illiouchine.toothbrush.di

import android.content.Context
import androidx.room.Room
import com.illiouchine.toothbrush.database.AppDatabase
import com.illiouchine.toothbrush.database.BrushHistoryDataMapper
import com.illiouchine.toothbrush.database.CountDownDurationDataMapper
import com.illiouchine.toothbrush.database.datasource.brushhistory.BrushHistoryDataSource
import com.illiouchine.toothbrush.database.datasource.coundown.CountDownDataSource
import com.illiouchine.toothbrush.database.datasource.coundown.CountDownDataSourceInMemory
import com.illiouchine.toothbrush.database.datasource.coundown.CountDownDataSourceSharedPref
import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import com.illiouchine.toothbrush.usecase.datagateway.CountDownDurationDataGateway
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StatisticsModule {

    @Singleton
    @Provides
    fun bindStatisticsDataSource(
        //statisticsInMemory: BrushHistoryInMemory
        appDatabase: AppDatabase
    ): BrushHistoryDataSource{
        return appDatabase.brushHistoryDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
class SettingsModule {

    /*
    @Singleton
    @Binds
    abstract fun bindSettingsDataSource(
        countDownDataSourceInMemory: CountDownDataSourceInMemory
    ): CountDownDataSource
    */

    @Singleton
    @Provides
    fun provideCountDownDataSource(@ApplicationContext appContext: Context): CountDownDataSource {
        return CountDownDataSourceSharedPref(appContext)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "appDatabase"
        ).build()
    }

    @Provides
    fun bindStatisticsDataGateway(
        brushHistoryDataSource: BrushHistoryDataSource
    ): BrushHistoryDataGateway {
        return BrushHistoryDataMapper(
            brushHistoryDataSource = brushHistoryDataSource
        )
    }

    @Provides
    fun bindSettingsDataGateway(
        countDownDataSource: CountDownDataSource
    ): CountDownDurationDataGateway {
        return CountDownDurationDataMapper(
            countDownDataSource = countDownDataSource
        )
    }
}