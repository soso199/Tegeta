package com.example.tegeta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tegeta.data.dao.CurrentCarsDao
import com.example.tegeta.data.dao.ServicesDao
import com.example.tegeta.data.model.CurrentCar
import com.example.tegeta.data.model.ServiceType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext


@Database(entities = [CurrentCar::class, ServiceType::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currentCarsDao(): CurrentCarsDao

    abstract fun servicesDao(): ServicesDao

    companion object {
        private const val DATABASE_NAME = "tegeta-db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            instance?.let {
                                CoroutineScope(EmptyCoroutineContext).launch {
                                    for (service in DataGenerator.getServices()) {
                                        it.servicesDao().insertService(service)
                                    }
                                }

                            }
                        }
                    }
                ).build()
        }
    }

}
