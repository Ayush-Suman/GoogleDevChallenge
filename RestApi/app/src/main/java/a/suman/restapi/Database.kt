package a.suman.restapi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [table::class], version = 1, exportSchema = false)
abstract class Database :RoomDatabase(){

    abstract fun tmethods(): tMethods


    companion object {
        @Volatile
        private var INSTANCE: a.suman.restapi.Database? = null

        fun getDatabase(context: Context): a.suman.restapi.Database {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    a.suman.restapi.Database::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}



