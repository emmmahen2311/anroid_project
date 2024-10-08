package com.example.kotlinfinal


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinfinal.Model.Training
import com.example.kotlinfinal.Model.User
import com.example.kotlinfinal.TrainingDao
import com.example.kotlinfinal.UserDao

@Database(entities = [User::class, Training::class], version = 2, exportSchema = false)
abstract class AppLocalDb : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun trainingDao(): TrainingDao

    companion object {
        @Volatile
        private var INSTANCE: AppLocalDb? = null

        fun getDatabase(context: Context): AppLocalDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppLocalDb::class.java,
                    "app_local_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
