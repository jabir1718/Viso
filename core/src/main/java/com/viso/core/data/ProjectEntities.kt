package com.viso.core.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "project")
data class ProjectEntity(
    @PrimaryKey val id: String,
    val name: String,
    val createdAtEpochMs: Long,
    val updatedAtEpochMs: Long,
    val frameRateNumerator: Int = 30,
    val frameRateDenominator: Int = 1,
    val width: Int = 1080,
    val height: Int = 1920
)

@Entity(tableName = "track", indices = [Index("projectId"), Index(value = ["projectId", "sortOrder"])],
    foreignKeys = [ForeignKey(entity = ProjectEntity::class, parentColumns = ["id"], childColumns = ["projectId"], onDelete = ForeignKey.CASCADE)])
data class TrackEntity(
    @PrimaryKey val id: String,
    val projectId: String,
    val name: String,
    val kind: String,
    val sortOrder: Int,
    val isLocked: Boolean = false,
    val isHidden: Boolean = false,
    val isMuted: Boolean = false,
    val isSolo: Boolean = false
)

@Entity(tableName = "asset_license", indices = [Index("projectId"), Index(value = ["projectId", "assetUri"], unique = true)],
    foreignKeys = [ForeignKey(entity = ProjectEntity::class, parentColumns = ["id"], childColumns = ["projectId"], onDelete = ForeignKey.CASCADE)])
data class AssetLicenseEntity(
    @PrimaryKey val id: String,
    val projectId: String,
    val assetUri: String,
    val source: String,
    val licenseType: String,
    val attribution: String? = null,
    val evidenceUri: String? = null,
    val importedAtEpochMs: Long
)

@Entity(tableName = "clip", indices = [Index("trackId"), Index("assetLicenseId"), Index(value = ["trackId", "startFrame"])],
    foreignKeys = [
        ForeignKey(entity = TrackEntity::class, parentColumns = ["id"], childColumns = ["trackId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = AssetLicenseEntity::class, parentColumns = ["id"], childColumns = ["assetLicenseId"], onDelete = ForeignKey.RESTRICT)
    ])
data class ClipEntity(
    @PrimaryKey val id: String,
    val trackId: String,
    val assetLicenseId: String,
    val startFrame: Long,
    val sourceInFrame: Long,
    val durationFrames: Long,
    val playbackRate: Float = 1f
)

@Entity(tableName = "effect", indices = [Index("clipId")],
    foreignKeys = [ForeignKey(entity = ClipEntity::class, parentColumns = ["id"], childColumns = ["clipId"], onDelete = ForeignKey.CASCADE)])
data class EffectEntity(
    @PrimaryKey val id: String,
    val clipId: String,
    val effectType: String,
    val enabled: Boolean = true,
    val parametersJson: String = "{}",
    val sortOrder: Int = 0
)

@Entity(tableName = "keyframe", indices = [Index("effectId"), Index(value = ["effectId", "parameter", "frame"], unique = true)],
    foreignKeys = [ForeignKey(entity = EffectEntity::class, parentColumns = ["id"], childColumns = ["effectId"], onDelete = ForeignKey.CASCADE)])
data class KeyframeEntity(
    @PrimaryKey val id: String,
    val effectId: String,
    val parameter: String,
    val frame: Long,
    val value: Double,
    val interpolation: String = "LINEAR"
)

@Entity(tableName = "lyric_line", indices = [Index("projectId"), Index(value = ["projectId", "startFrame"])],
    foreignKeys = [ForeignKey(entity = ProjectEntity::class, parentColumns = ["id"], childColumns = ["projectId"], onDelete = ForeignKey.CASCADE)])
data class LyricLineEntity(
    @PrimaryKey val id: String,
    val projectId: String,
    val text: String,
    val startFrame: Long,
    val endFrame: Long,
    val translation: String? = null
)

@Entity(tableName = "preset", indices = [Index("projectId")],
    foreignKeys = [ForeignKey(entity = ProjectEntity::class, parentColumns = ["id"], childColumns = ["projectId"], onDelete = ForeignKey.CASCADE)])
data class PresetEntity(
    @PrimaryKey val id: String,
    val projectId: String,
    val name: String,
    val category: String,
    val definitionJson: String,
    val createdAtEpochMs: Long
)

@Entity(tableName = "render_job", indices = [Index("projectId"), Index(value = ["status", "createdAtEpochMs"])],
    foreignKeys = [ForeignKey(entity = ProjectEntity::class, parentColumns = ["id"], childColumns = ["projectId"], onDelete = ForeignKey.CASCADE)])
data class RenderJobEntity(
    @PrimaryKey val id: String,
    val projectId: String,
    val status: String,
    val progressPercent: Int = 0,
    val outputUri: String? = null,
    val errorMessage: String? = null,
    val createdAtEpochMs: Long,
    val updatedAtEpochMs: Long
)
