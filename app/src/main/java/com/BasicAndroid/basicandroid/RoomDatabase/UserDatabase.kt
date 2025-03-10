package com.BasicAndroid.basicandroid.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null
        fun getDataBase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val passphrase: ByteArray =
                    net.sqlcipher.database.SQLiteDatabase.getBytes("YourSecretKey".toCharArray())
                val factory = SupportFactory(passphrase)
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).openHelperFactory(factory).build()
                INSTANCE = instance
                instance
            }
        }
    }
}