package com.viso.core.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ProjectEntity::class, TrackEntity::class, ClipEntity::class,
        EffectEntity::class, KeyframeEntity::class, LyricLineEntity::class,
        AssetLicenseEntity::class, PresetEntity::class, RenderJobEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class VisoDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun trackDao(): TrackDao
    abstract fun clipDao(): ClipDao
    abstract fun effectDao(): EffectDao
    abstract fun keyframeDao(): KeyframeDao
    abstract fun lyricLineDao(): LyricLineDao
    abstract fun assetLicenseDao(): AssetLicenseDao
    abstract fun presetDao(): PresetDao
    abstract fun renderJobDao(): RenderJobDao
}
