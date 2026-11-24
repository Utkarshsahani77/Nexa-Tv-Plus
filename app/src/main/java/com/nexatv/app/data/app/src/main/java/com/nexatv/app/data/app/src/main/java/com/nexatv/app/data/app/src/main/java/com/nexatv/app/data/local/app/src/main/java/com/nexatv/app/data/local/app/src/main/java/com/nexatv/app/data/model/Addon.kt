package com.nexatv.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addons")
data class Addon(
    @PrimaryKey val id: String,
    val name: String,
    val url: String,
    val type: String
)
