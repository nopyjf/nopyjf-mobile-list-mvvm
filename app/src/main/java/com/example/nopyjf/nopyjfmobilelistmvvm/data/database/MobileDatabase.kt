package com.example.nopyjf.nopyjfmobilelistmvvm.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nopyjf.nopyjfmobilelistmvvm.data.dao.FavoriteDao
import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.FavoriteEntity
import org.koin.dsl.module

val getMobileDatabaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            MobileDatabase::class.java,
            "mobile_database.db"
        ).build()
    }
}

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class MobileDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}