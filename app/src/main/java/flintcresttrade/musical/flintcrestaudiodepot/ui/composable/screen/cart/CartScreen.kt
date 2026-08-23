package flintcresttrade.musical.flintcrestaudiodepot.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import flintcresttrade.musical.flintcrestaudiodepot.R
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNContentWrapper
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
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    CartContent(
        state = state,
        total = total,
        modifier = modifier,
        onPlus = viewModel::incrementProductInCart,
        onMinus = viewModel::decrementItemInCart,
        onDelete = viewModel::deleteFromCart,
        onCheckout = onNavigateToCheckoutScreen,
    )
}

@Composable
private fun CartContent(
    state: DataUiState<List<CartItemUiState>>,
    total: Double,
    modifier: Modifier,
    onPlus: (Int) -> Unit,
    onMinus: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onCheckout: () -> Unit,
) {
    WHDBNContentWrapper(
        dataState = state,
        dataPopulated = {
            val items = (state as DataUiState.Populated).data
            Column(modifier.fillMaxSize().padding(16.dp)) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f),
                ) {
                    items(items, key = { it.productId }) { item ->
                        CartRow(item, onPlus, onMinus, onDelete)
                    }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(stringResource(R.string.whdbn_total), style = MaterialTheme.typography.titleLarge)
                    Text("£%.2f".format(total), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                }
                Spacer(Modifier.height(12.dp))
                Button(onClick = onCheckout, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.whdbn_proceed_checkout))
                }
            }
        },
        dataEmpty = {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(Icons.Default.ShoppingBag, null, Modifier.size(80.dp), tint = MaterialTheme.colorScheme.primary)
                Text(stringResource(R.string.whdbn_cart_state_empty_primary_text), style = MaterialTheme.typography.titleLarge)
                Text(stringResource(R.string.whdbn_start_shopping), color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        },
    )
}

@Composable
private fun CartRow(item: CartItemUiState, onPlus: (Int) -> Unit, onMinus: (Int) -> Unit, onDelete: (Int) -> Unit) {
    Card(shape = RoundedCornerShape(14.dp)) {
        Row(Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(item.productImageUrl, item.productTitle, Modifier.size(72.dp), contentScale = ContentScale.Crop)
            Column(Modifier.weight(1f).padding(horizontal = 10.dp)) {
                Text(item.productTitle, maxLines = 2, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.SemiBold)
                Text("£%.2f".format(item.productPrice), color = MaterialTheme.colorScheme.primary)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { if (item.quantity == 1) onDelete(item.productId) else onMinus(item.productId) }) {
                        Icon(Icons.Default.Remove, stringResource(R.string.whdbn_decrease_quantity_icon_description))
                    }
                    Text(item.quantity.toString(), fontWeight = FontWeight.Bold)
                    IconButton(onClick = { onPlus(item.productId) }) {
                        Icon(Icons.Default.Add, stringResource(R.string.whdbn_increase_quantity_icon_description))
                    }
                }
            }
            IconButton(onClick = { onDelete(item.productId) }) {
                Icon(Icons.Default.Delete, stringResource(R.string.whdbn_delete_item_icon_description))
            }
        }
    }
}
