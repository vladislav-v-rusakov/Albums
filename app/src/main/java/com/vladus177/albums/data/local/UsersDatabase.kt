package vladus177.com.albums.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import vladus177.com.albums.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao
}