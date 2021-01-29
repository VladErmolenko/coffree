package com.example.roomapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation

data class CafeWithUsers(
    @Embedded val cafe: Cafe,
    @Relation(
            parentColumn = "assortmentId",
            entityColumn = "workCafeId"
    )
    val users: List<User>
)
