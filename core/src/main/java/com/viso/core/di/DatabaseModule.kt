package com.viso.core.di

import android.content.Context
import androidx.room.Room
import com.viso.core.data.VisoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): VisoDatabase =
        Room.databaseBuilder(context, VisoDatabase::class.java, "viso.db").build()

    @Provides fun provideProjectDao(db: VisoDatabase) = db.projectDao()
    @Provides fun provideTrackDao(db: VisoDatabase) = db.trackDao()
    @Provides fun provideClipDao(db: VisoDatabase) = db.clipDao()
    @Provides fun provideEffectDao(db: VisoDatabase) = db.effectDao()
    @Provides fun provideKeyframeDao(db: VisoDatabase) = db.keyframeDao()
    @Provides fun provideLyricLineDao(db: VisoDatabase) = db.lyricLineDao()
    @Provides fun provideAssetLicenseDao(db: VisoDatabase) = db.assetLicenseDao()
    @Provides fun providePresetDao(db: VisoDatabase) = db.presetDao()
    @Provides fun provideRenderJobDao(db: VisoDatabase) = db.renderJobDao()
}
