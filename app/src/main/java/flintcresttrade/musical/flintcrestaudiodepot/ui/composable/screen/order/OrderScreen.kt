package flintcresttrade.musical.flintcrestaudiodepot.ui.composable.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import flintcresttrade.musical.flintcrestaudiodepot.R
import flintcresttrade.musical.flintcrestaudiodepot.data.entity.OrderEntity
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNContentWrapper
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNEmptyView
import flintcresttrade.musical.flintcrestaudiodepot.ui.state.DataUiState
import flintcresttrade.musical.flintcrestaudiodepot.ui.theme.BrandSuccess
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.OrderViewModel
import java.time.format.DateTimeFormatter
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val state by viewModel.ordersState.collectAsState()
    WHDBNContentWrapper(
        dataState = state,
        dataPopulated = {
            val orders = (state as DataUiState.Populated).data.sortedByDescending { it.timestamp }
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(orders, key = { it.orderNumber }) { order -> OrderCard(order) }
            }
        },
        dataEmpty = {
            WHDBNEmptyView(
                modifier = modifier.fillMaxSize(),
                primaryText = stringResource(R.string.whdbn_orders_state_empty_primary_text),
            )
        },
    )
}

@Composable
private fun OrderCard(order: OrderEntity) {
    Card(shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Order #${order.orderNumber}", fontWeight = FontWeight.Bold)
                Surface(color = BrandSuccess.copy(alpha = 0.12f), shape = RoundedCornerShape(50)) {
                    Text("Reserved", Modifier.padding(10.dp, 4.dp), color = BrandSuccess)
                }
            }
            Text(order.timestamp.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")), color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(order.description, style = MaterialTheme.typography.bodyMedium)
            Text("£%.2f".format(order.price), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Text(stringResource(R.string.whdbn_order_pickup_banner), style = MaterialTheme.typography.bodyMedium, color = BrandSuccess)
        }
    }
}
