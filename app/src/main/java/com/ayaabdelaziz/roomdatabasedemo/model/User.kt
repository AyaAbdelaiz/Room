package com.ayaabdelaziz.roomdatabasedemo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_data")
data class User(
    var firstName: String,
    var secondName: String, var age: Int, @PrimaryKey(autoGenerate = true) var id: Int
) : Parcelable
