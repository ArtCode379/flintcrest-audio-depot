package flintcresttrade.musical.flintcrestaudiodepot.di

import flintcresttrade.musical.flintcrestaudiodepot.data.datastore.WHDBNOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { WHDBNOnboardingPrefs(androidContext()) }
}