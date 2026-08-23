package flintcresttrade.musical.flintcrestaudiodepot.di

import androidx.room.Room
import flintcresttrade.musical.flintcrestaudiodepot.data.database.WHDBNDatabase
import org.koin.dsl.module

private const val DB_NAME = "whdbn_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = WHDBNDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<WHDBNDatabase>().cartItemDao() }

    single { get<WHDBNDatabase>().orderDao() }
}