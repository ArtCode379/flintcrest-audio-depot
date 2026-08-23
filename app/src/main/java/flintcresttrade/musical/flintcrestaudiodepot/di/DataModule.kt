package flintcresttrade.musical.flintcrestaudiodepot.di

import flintcresttrade.musical.flintcrestaudiodepot.data.repository.CartRepository
import flintcresttrade.musical.flintcrestaudiodepot.data.repository.WHDBNOnboardingRepo
import flintcresttrade.musical.flintcrestaudiodepot.data.repository.OrderRepository
import flintcresttrade.musical.flintcrestaudiodepot.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        WHDBNOnboardingRepo(
            whdbnOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}