package com.example.bankasiaproject.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userResponse: UserResponse)

    @Query("select * from user_info where id=id")
    fun getUserInfo(): LiveData<UserResponse>
}