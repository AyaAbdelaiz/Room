package com.ayaabdelaziz.roomdatabasedemo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ayaabdelaziz.roomdatabasedemo.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        var instance: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val Instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_datavbase"
                ).build()
                instance = Instance
                return Instance

            }

        }
    }

}