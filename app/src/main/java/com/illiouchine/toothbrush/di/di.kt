package com.illiouchine.toothbrush.di

import android.content.Context
import androidx.room.Room
import com.illiouchine.toothbrush.database.AchievementDataMapper
import com.illiouchine.toothbrush.database.CountDownDurationDataMapper
import com.illiouchine.toothbrush.database.ReminderDataMapper
import com.illiouchine.toothbrush.database.StatisticsDataMapper
import com.illiouchine.toothbrush.database.datasource.achievement.AchievementDataSource
import com.illiouchine.toothbrush.database.datasource.coundown.CountDownDataSource
import com.illiouchine.toothbrush.database.datasource.coundown.CountDownDataSourceSharedPref
import com.illiouchine.toothbrush.database.datasource.reminder.ReminderDataSource
import com.illiouchine.toothbrush.database.datasource.reminder.ReminderDataSourceSharedPref
import com.illiouchine.toothbrush.database.datasource.statistics.StatisticsDataSource
import com.illiouchine.toothbrush.database.room.AppDatabase
import com.illiouchine.toothbrush.database.room.MigrationFrom1To2
import com.illiouchine.toothbrush.usecase.datagateway.AchievementDataGateway
import com.illiouchine.toothbrush.usecase.datagateway.CountDownDurationDataGateway
import com.illiouchine.toothbrush.usecase.datagateway.ReminderDataGateway
import com.illiouchine.toothbrush.usecase.datagateway.StatisticsDataGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AchievementsModule {

    @Singleton
    @Provides
    fun provideAchievementDataSource(
        appDatabase: AppDatabase
    ): AchievementDataSource {
        return appDatabase.achievementDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
class StatisticsModule {

    @Singleton
    @Provides
    fun bindStatisticsDataSource(
        //statisticsInMemory: BrushHistoryInMemory
        appDatabase: AppDatabase
    ): StatisticsDataSource {
        return appDatabase.statisticDao()
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
class ReminderModule {

    @Singleton
    @Provides
    fun provideReminderDataSource(@ApplicationContext appContext: Context): ReminderDataSource {
        return ReminderDataSourceSharedPref(appContext)
    }
}


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room
            .databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "appDatabase"
            )
            .addMigrations(
                MigrationFrom1To2
            )
            .build()
    }

    @Provides
    fun provideAchievementDataGateway(
        achievementDataSource: AchievementDataSource
    ): AchievementDataGateway {
        return AchievementDataMapper(
            achievementDataSource = achievementDataSource
        )
    }

    @Provides
    fun provideReminderDataGateway(
        reminderDataSource: ReminderDataSource
    ): ReminderDataGateway {
        return ReminderDataMapper(
            reminderDataSource = reminderDataSource
        )
    }

    @Provides
    fun bindStatisticsDataGateway(
        statisticsDataSource: StatisticsDataSource
    ): StatisticsDataGateway {
        return StatisticsDataMapper(
            statisticsDataSource = statisticsDataSource
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