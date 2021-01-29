package com.example.roomapp.model

import android.os.ParcelFileDescriptor
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "assortment_table")
data class Assortment(
    @PrimaryKey(autoGenerate = true)
    val assortmentId: Int,
    val name:String,
    val price:String,
    val description: String
): Parcelable