package com.viso.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM project ORDER BY updatedAtEpochMs DESC") fun observeAll(): Flow<List<ProjectEntity>>
    @Query("SELECT * FROM project WHERE id = :id") suspend fun findById(id: String): ProjectEntity?
    @Insert(onConflict = OnConflictStrategy.ABORT) suspend fun insert(project: ProjectEntity)
    @Query("UPDATE project SET name = :name, updatedAtEpochMs = :updatedAt WHERE id = :id")
    suspend fun rename(id: String, name: String, updatedAt: Long): Int
    @Query("DELETE FROM project WHERE id = :id") suspend fun delete(id: String): Int
}

@Dao
interface TrackDao {
    @Query("SELECT * FROM track WHERE projectId = :projectId ORDER BY sortOrder") fun observeForProject(projectId: String): Flow<List<TrackEntity>>
    @Insert(onConflict = OnConflictStrategy.ABORT) suspend fun insert(track: TrackEntity)
    @Query("UPDATE track SET isLocked=:locked, isHidden=:hidden, isMuted=:muted, isSolo=:solo WHERE id=:id")
    suspend fun updateState(id: String, locked: Boolean, hidden: Boolean, muted: Boolean, solo: Boolean): Int
}

@Dao
interface AssetLicenseDao {
    @Query("SELECT * FROM asset_license WHERE projectId=:projectId ORDER BY importedAtEpochMs DESC")
    fun observeForProject(projectId: String): Flow<List<AssetLicenseEntity>>
    @Query("SELECT * FROM asset_license WHERE id=:id") suspend fun findById(id: String): AssetLicenseEntity?
    @Insert(onConflict = OnConflictStrategy.ABORT) suspend fun insert(license: AssetLicenseEntity)
}

@Dao
interface ClipDao {
    @Query("SELECT * FROM clip WHERE trackId=:trackId ORDER BY startFrame") fun observeForTrack(trackId: String): Flow<List<ClipEntity>>
    @Insert(onConflict = OnConflictStrategy.ABORT) suspend fun insert(clip: ClipEntity)
    @Query("UPDATE clip SET startFrame=:start, sourceInFrame=:sourceIn, durationFrames=:duration WHERE id=:id")
    suspend fun updateTiming(id: String, start: Long, sourceIn: Long, duration: Long): Int
    @Query("DELETE FROM clip WHERE id=:id") suspend fun delete(id: String): Int
}

@Dao
interface EffectDao {
    @Query("SELECT * FROM effect WHERE clipId=:clipId ORDER BY sortOrder") fun observeForClip(clipId: String): Flow<List<EffectEntity>>
    @Insert(onConflict = OnConflictStrategy.ABORT) suspend fun insert(effect: EffectEntity)
    @Query("UPDATE effect SET enabled=:enabled, parametersJson=:json WHERE id=:id")
    suspend fun update(id: String, enabled: Boolean, json: String): Int
}

@Dao
interface KeyframeDao {
    @Query("SELECT * FROM keyframe WHERE effectId=:effectId AND parameter=:parameter ORDER BY frame")
    fun observeParameter(effectId: String, parameter: String): Flow<List<KeyframeEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun put(keyframe: KeyframeEntity)
    @Query("DELETE FROM keyframe WHERE id=:id") suspend fun delete(id: String): Int
}

@Dao
interface LyricLineDao {
    @Query("SELECT * FROM lyric_line WHERE projectId=:projectId ORDER BY startFrame")
    fun observeForProject(projectId: String): Flow<List<LyricLineEntity>>
    @Insert(onConflict = OnConflictStrategy.ABORT) suspend fun insert(line: LyricLineEntity)
    @Query("UPDATE lyric_line SET text=:text, startFrame=:start, endFrame=:end WHERE id=:id")
    suspend fun update(id: String, text: String, start: Long, end: Long): Int
}

@Dao
interface PresetDao {
    @Query("SELECT * FROM preset WHERE projectId=:projectId ORDER BY name") fun observeForProject(projectId: String): Flow<List<PresetEntity>>
    @Insert(onConflict = OnConflictStrategy.ABORT) suspend fun insert(preset: PresetEntity)
    @Query("DELETE FROM preset WHERE id=:id") suspend fun delete(id: String): Int
}

@Dao
interface RenderJobDao {
    @Query("SELECT * FROM render_job WHERE projectId=:projectId ORDER BY createdAtEpochMs DESC")
    fun observeForProject(projectId: String): Flow<List<RenderJobEntity>>
    @Query("SELECT * FROM render_job WHERE status IN ('QUEUED','RUNNING') ORDER BY createdAtEpochMs")
    suspend fun findPending(): List<RenderJobEntity>
    @Insert(onConflict = OnConflictStrategy.ABORT) suspend fun insert(job: RenderJobEntity)
    @Query("UPDATE render_job SET status=:status, progressPercent=:progress, updatedAtEpochMs=:updatedAt, errorMessage=:error WHERE id=:id")
    suspend fun updateProgress(id: String, status: String, progress: Int, updatedAt: Long, error: String?): Int
}
