package flintcresttrade.musical.flintcrestaudiodepot.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import flintcresttrade.musical.flintcrestaudiodepot.R
import flintcresttrade.musical.flintcrestaudiodepot.data.entity.OrderEntity
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNContentWrapper
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNEmptyView
import flintcresttrade.musical.flintcrestaudiodepot.ui.state.DataUiState
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        WHDBNContentWrapper(
            dataState = ordersState,

            dataPopulated = {
                val data = (ordersState as DataUiState.Populated).data

            },

            dataEmpty = {
                WHDBNEmptyView(
                    primaryText = stringResource(R.string.whdbn_orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}