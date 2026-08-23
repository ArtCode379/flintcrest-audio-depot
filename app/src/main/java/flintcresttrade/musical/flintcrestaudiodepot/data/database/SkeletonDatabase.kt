package flintcresttrade.musical.flintcrestaudiodepot.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import flintcresttrade.musical.flintcrestaudiodepot.data.dao.CartItemDao
import flintcresttrade.musical.flintcrestaudiodepot.data.dao.OrderDao
import flintcresttrade.musical.flintcrestaudiodepot.data.database.converter.Converters
import flintcresttrade.musical.flintcrestaudiodepot.data.entity.CartItemEntity
import flintcresttrade.musical.flintcrestaudiodepot.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WHDBNDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}