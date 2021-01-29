package com.example.roomapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "user_table",foreignKeys = [
        ForeignKey(entity = Cafe::class,
            parentColumns = ["cafeId"],
            childColumns = ["workCafeId"],
            onDelete = CASCADE)])
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val workCafeId: Int,

    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val access:Boolean,
    val role: Boolean
) : Parcelable