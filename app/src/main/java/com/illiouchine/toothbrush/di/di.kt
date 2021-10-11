package com.illiouchine.toothbrush.di

import com.illiouchine.toothbrush.database.BrushHistoryDataMapper
import com.illiouchine.toothbrush.database.datasource.brushhistory.BrushHistoryDataSource
import com.illiouchine.toothbrush.database.datasource.brushhistory.BrushHistoryInMemory
import com.illiouchine.toothbrush.usecase.datagateway.BrushHistoryDataGateway
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StatisticsModule {

    @Singleton
    @Binds
    abstract fun bindStatisticsDataSource(
        statisticsInMemory: BrushHistoryInMemory
    ): BrushHistoryDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun bindStatisticsDataGateway(
        brushHistoryDataSource: BrushHistoryDataSource
    ): BrushHistoryDataGateway {
        return BrushHistoryDataMapper(
            brushHistoryDataSource = brushHistoryDataSource
        )
    }
}