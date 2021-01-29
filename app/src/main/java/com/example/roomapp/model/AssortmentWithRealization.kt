package com.example.roomapp.model

import androidx.room.Embedded
import androidx.room.Relation

data class AssortmentWithRealization (
    @Embedded val assortment: Assortment,
    @Relation(
        parentColumn = "cafeId",
        entityColumn = "realizationAssortmentId"
    )
    val realization: List<Realization>
)