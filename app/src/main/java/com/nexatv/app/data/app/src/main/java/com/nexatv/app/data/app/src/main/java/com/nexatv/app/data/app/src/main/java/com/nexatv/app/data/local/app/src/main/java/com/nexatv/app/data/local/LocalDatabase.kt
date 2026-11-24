package com.nexatv.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nexatv.app.data.model.Addon

@Database(
    entities = [Addon::class],
    version = 1,
    exportSchema = false
)

abstract class LocalDatabase : RoomDatabase() {

    abstract fun addonDao(): AddonDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun get(context: Context): LocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "nexa_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
