package flintcresttrade.musical.flintcrestaudiodepot.ui.composable.screen.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import flintcresttrade.musical.flintcrestaudiodepot.R
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNContentWrapper
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNEmptyView
import flintcresttrade.musical.flintcrestaudiodepot.ui.state.CartItemUiState
import flintcresttrade.musical.flintcrestaudiodepot.ui.state.DataUiState
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()

    val onPlusItemClick = { itemId: Int ->
        viewModel.incrementProductInCart(itemId)
    }

    val onMinusItemClick = { itemId: Int ->
        viewModel.decrementItemInCart(itemId)
    }

    CartScreenContent(
        cartItemsState = cartItemsState,
        modifier = modifier,
        totalPrice = totalPrice,
        onPlusItemClick = onPlusItemClick,
        onMinusItemClick = onMinusItemClick,
        onCompleteOrderButtonClick = onNavigateToCheckoutScreen,
    )
}

@Composable
private fun CartScreenContent(
    cartItemsState: DataUiState<List<CartItemUiState>>,
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onPlusItemClick: (Int) -> Unit,
    onMinusItemClick: (Int) -> Unit,
    onCompleteOrderButtonClick: () -> Unit,
) {
    Column(modifier = modifier) {

        WHDBNContentWrapper(
            dataState = cartItemsState,

            dataPopulated = {
                val data = (cartItemsState as DataUiState.Populated).data

            },

            dataEmpty = {
                WHDBNEmptyView(
                    primaryText = stringResource(R.string.whdbn_cart_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}