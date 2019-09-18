package com.anangkur.jetpacksubmission1.data.local.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anangkur.jetpacksubmission1.data.model.Result

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsert(data: Result)

    @Query("SELECT * FROM DATABASE_RESULT WHERE COLUMN_TYPE == :type")
    suspend fun getAllResult(type: Int): List<Result>

    @Query("DELETE FROM DATABASE_RESULT WHERE COLUMN_ID == :id")
    suspend fun deleteById(id: Int): Int

    @Query("SELECT * FROM DATABASE_RESULT WHERE COLUMN_ID == :id")
    suspend fun getResultById(id: Int): Result
}