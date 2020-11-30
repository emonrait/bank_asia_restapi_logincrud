package com.example.bankasiaproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_info")
data class UserResponse(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String?,
    @ColumnInfo(name = "mobile")
    @SerializedName("mobile")
    var mobile: String?,
    @ColumnInfo(name = "password")
    @SerializedName("password")
    var password: String?,
    @ColumnInfo(name = "imagelink")
    @SerializedName("imagelink")
    var imagelink: String?,
    @ColumnInfo(name = "balance")
    @SerializedName("balance")
    var balance: String?


)