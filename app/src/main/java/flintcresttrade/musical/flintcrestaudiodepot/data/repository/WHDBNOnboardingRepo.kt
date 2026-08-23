package flintcresttrade.musical.flintcrestaudiodepot.data.repository

import flintcresttrade.musical.flintcrestaudiodepot.data.datastore.WHDBNOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WHDBNOnboardingRepo(
    private val whdbnOnboardingStoreManager: WHDBNOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return whdbnOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            whdbnOnboardingStoreManager.setOnboardedState(state)
        }
    }
}