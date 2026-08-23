package flintcresttrade.musical.flintcrestaudiodepot.data.model

import androidx.annotation.StringRes
import flintcresttrade.musical.flintcrestaudiodepot.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    GUITARS(R.string.whdbn_category_guitars),
    KEYS(R.string.whdbn_category_keys),
    DRUMS(R.string.whdbn_category_drums),
    MICROPHONES(R.string.whdbn_category_microphones),
    HEADPHONES(R.string.whdbn_category_headphones),
    SPEAKERS(R.string.whdbn_category_speakers),
    INTERFACES(R.string.whdbn_category_interfaces),
}
