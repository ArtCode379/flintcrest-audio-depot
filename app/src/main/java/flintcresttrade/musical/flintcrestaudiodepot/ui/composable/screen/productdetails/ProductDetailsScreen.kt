package flintcresttrade.musical.flintcrestaudiodepot.ui.composable.screen.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import flintcresttrade.musical.flintcrestaudiodepot.R
import flintcresttrade.musical.flintcrestaudiodepot.data.model.Product
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNContentWrapper
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNEmptyView
import flintcresttrade.musical.flintcrestaudiodepot.ui.state.DataUiState
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val productState by viewModel.productDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeProductDetails(productId)
    }

    ProductDetailsScreenContent(
        productState = productState,
        modifier = modifier,
        onAddToCart = viewModel::addProductToCart
    )
}

@Composable
private fun ProductDetailsScreenContent(
    productState: DataUiState<Product>,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit,
) {
    Column(modifier = modifier) {

        WHDBNContentWrapper(
            dataState = productState,

            dataPopulated = {
                val data = (productState as DataUiState.Populated).data

            },

            dataEmpty = {
                WHDBNEmptyView(
                    primaryText = stringResource(R.string.whdbn_product_details_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}