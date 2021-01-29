package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.model.*

@Dao
interface CafeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAssortment(assortment: Assortment)

    @Update
    suspend fun updateAssortment(assortment: Assortment)

    @Delete
    suspend fun deleteAssortment(assortment: Assortment)

    @Query("DELETE FROM assortment_table")
    suspend fun deleteAllAssortment()

    @Query("SELECT * FROM assortment_table ORDER BY assortmentId ASC")
    fun readAllAssortmentData(): LiveData<List<Assortment>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCafe(cafe: Cafe)

    @Update
    suspend fun updateCafe(cafe: Cafe)

    @Delete
    suspend fun deleteCafe(cafe: Cafe)

    @Query("DELETE FROM cafe_table")
    suspend fun deleteAllCafe()

    @Query("SELECT * FROM cafe_table ORDER BY cafeId ASC")
    fun readAllCafeData(): LiveData<List<Cafe>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRealization(realization: Realization)

    @Update
    suspend fun updateRealization(realization: Realization)

    @Delete
    suspend fun deleteRealization(realization: Realization)

    @Query("DELETE FROM realization_table")
    suspend fun deleteAllRealization()

    @Query("SELECT * FROM realization_table ORDER BY realizationId ASC")
    fun readAllRealization(): LiveData<List<Realization>>

}