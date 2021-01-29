package com.example.roomapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cafe_table")
data class Cafe(

    @PrimaryKey(autoGenerate = true)
    val cafeId: Int,

    val address:String,
    val name:String,
    val profit:String
): Parcelable
