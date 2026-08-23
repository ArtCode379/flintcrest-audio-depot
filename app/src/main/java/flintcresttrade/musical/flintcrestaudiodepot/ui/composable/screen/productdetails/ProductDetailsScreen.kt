package flintcresttrade.musical.flintcrestaudiodepot.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import flintcresttrade.musical.flintcrestaudiodepot.R
import flintcresttrade.musical.flintcrestaudiodepot.data.model.Product
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNContentWrapper
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNEmptyView
import flintcresttrade.musical.flintcrestaudiodepot.ui.state.DataUiState
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.ProductDetailsViewModel
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.productDetailsState.collectAsState()
    LaunchedEffect(productId) { viewModel.observeProductDetails(productId) }
    ProductDetailsContent(state, modifier, viewModel::addProductToCart)
}

@Composable
private fun ProductDetailsContent(
    state: DataUiState<Product>,
    modifier: Modifier,
    onAddToCart: () -> Unit,
) {
    var cartAdded by remember { mutableStateOf(false) }
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    Box(modifier.fillMaxSize()) {
        WHDBNContentWrapper(
            dataState = state,
            dataPopulated = {
                val product = (state as DataUiState.Populated).data
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                ) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = product.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
                    )
                    Column(Modifier.padding(20.dp)) {
                        Surface(color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(50)) {
                            Text(stringResource(product.category.titleRes), Modifier.padding(12.dp, 6.dp), color = MaterialTheme.colorScheme.primary)
                        }
                        Spacer(Modifier.height(14.dp))
                        Text(product.title, style = MaterialTheme.typography.titleLarge)
                        Text("£%.2f".format(product.price), style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.height(16.dp))
                        Text(product.description, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(Modifier.height(24.dp))
                        Text(stringResource(R.string.whdbn_reservation_note), style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(20.dp))
                        Button(
                            onClick = {
                                onAddToCart()
                                cartAdded = true
                            },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(stringResource(R.string.whdbn_button_add_to_cart_label))
                        }
                        Spacer(Modifier.height(70.dp))
                    }
                }
            },
            dataEmpty = {
                WHDBNEmptyView(
                    modifier = Modifier.fillMaxSize(),
                    primaryText = stringResource(R.string.whdbn_product_details_state_empty_primary_text),
                )
            },
        )
        AnimatedVisibility(
            visible = cartAdded,
            enter = slideInVertically { it },
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(Icons.Default.CheckCircle, null, tint = MaterialTheme.colorScheme.onPrimary)
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.whdbn_added_to_cart), color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Medium)
            }
        }
    }
}
