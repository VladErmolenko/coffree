package com.example.roomapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.PropertyKey

@Parcelize
@Entity(tableName = "realization_table",foreignKeys = [
        androidx.room.ForeignKey(
            entity = com.example.roomapp.model.Assortment::class,
            parentColumns = ["assortmentId"],
            childColumns = ["realizationAssortmentId"],
            onDelete = androidx.room.ForeignKey.CASCADE
        ),androidx.room.ForeignKey(
        entity = com.example.roomapp.model.User::class,
        parentColumns = ["id"],
        childColumns = ["realizationEmployeeId"],
        onDelete = androidx.room.ForeignKey.CASCADE
    ),androidx.room.ForeignKey(
        entity = com.example.roomapp.model.Cafe::class,
        parentColumns = ["cafeId"],
        childColumns = ["realizationCafeId"],
        onDelete = androidx.room.ForeignKey.CASCADE
    )])
data class Realization(

    @PrimaryKey(autoGenerate = true)
    val realizationId: Int,

    val realizationAssortmentId:Int,

    val realizationEmployeeId:Int,

    val realizationCafeId:Int,

    val date:String,
    val notes:String
): Parcelable

