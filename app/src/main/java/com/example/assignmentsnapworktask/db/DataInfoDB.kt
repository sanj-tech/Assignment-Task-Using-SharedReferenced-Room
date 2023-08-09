package com.example.assignmentsnapworktask.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignmentsnapworktask.dao.DateInfoInterface
import com.example.assignmentsnapworktask.model.DateInfo


@Database(entities = [DateInfo::class], version = 1,exportSchema = false)
abstract class DataInfoDB : RoomDatabase() {
    companion object {
        private var instance: DataInfoDB? = null

        @Synchronized
        fun getInstance(ctx: Context): DataInfoDB {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, DataInfoDB::class.java,
                    "user_database").build()

            return instance!!
        }
    }

    abstract fun dateInfoInterface():DateInfoInterface
}


//@Database(entities = [DateInfo::class], version = 1)
//abstract class DataInfoDB : RoomDatabase() {
//    abstract fun dateInfoInterface():DateInfoInterface
//
//    companion object {
//        private const val DATABASE_NAME = "entry_database"
//
//        @Volatile
//        private var INSTANCE: DataInfoDB? = null
//
//        fun getDatabase(context: Context): DataInfoDB {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DataInfoDB::class.java,
//                    DATABASE_NAME
//                ).build()
//
//                return instance
//            }
//        }
//    }
//}
