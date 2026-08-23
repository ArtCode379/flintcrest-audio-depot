package flintcresttrade.musical.flintcrestaudiodepot.ui.composable.screen.home

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import flintcresttrade.musical.flintcrestaudiodepot.R
import flintcresttrade.musical.flintcrestaudiodepot.data.model.Product
import flintcresttrade.musical.flintcrestaudiodepot.data.model.ProductCategory
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNContentWrapper
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.shared.WHDBNEmptyView
import flintcresttrade.musical.flintcrestaudiodepot.ui.state.DataUiState
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.ProductViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val state by viewModel.productsState.collectAsState()
    HomeContent(state, modifier, onNavigateToProductDetails)
}

@Composable
private fun HomeContent(
    state: DataUiState<List<Product>>,
    modifier: Modifier,
    onProductClick: (Int) -> Unit,
) {
    WHDBNContentWrapper(
        dataState = state,
        dataPopulated = {
            val products = (state as DataUiState.Populated).data
            var selected by remember { mutableStateOf<ProductCategory?>(null) }
            val visible = products.filter { selected == null || it.category == selected }
            val featured = products.take(4)
            val pagerState = rememberPagerState { featured.size }

            LaunchedEffect(pagerState.currentPage) {
                delay(4000)
                pagerState.animateScrollToPage((pagerState.currentPage + 1) % featured.size)
            }

            Column(modifier = modifier.fillMaxSize()) {
                Text(
                    text = stringResource(R.string.whdbn_home_intro),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(16.dp, 12.dp),
                )
                HorizontalPager(
                    state = pagerState,
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
                    pageSpacing = 12.dp,
                ) { index ->
                    HeroCard(featured[index], onProductClick)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp),
                ) {
                    featured.indices.forEach { index ->
                        Spacer(
                            Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(
                                    if (index == pagerState.currentPage) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.outline
                                    },
                                ),
                        )
                    }
                }
                LazyRow(
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    item {
                        CategoryChip(stringResource(R.string.whdbn_category_all), selected == null) { selected = null }
                    }
                    items(ProductCategory.entries) { category ->
                        CategoryChip(stringResource(category.titleRes), selected == category) { selected = category }
                    }
                }
                Text(
                    text = stringResource(R.string.whdbn_shop_collection),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp, 14.dp, 16.dp, 8.dp),
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(visible, key = { it.id }) { product ->
                        ProductCard(product, onProductClick)
                    }
                }
            }
        },
        dataEmpty = {
            WHDBNEmptyView(
                modifier = Modifier.fillMaxSize(),
                primaryText = stringResource(R.string.whdbn_products_state_empty_primary_text),
            )
        },
    )
}

@Composable
private fun HeroCard(product: Product, onClick: (Int) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable { onClick(product.id) },
    ) {
        Box {
            AsyncImage(product.imageUrl, null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(product.title, color = Color.White, style = MaterialTheme.typography.titleLarge)
                Text("£%.2f".format(product.price), color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun CategoryChip(label: String, selected: Boolean, onClick: () -> Unit) {
    AssistChip(
        onClick = onClick,
        label = { Text(label) },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            labelColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
        ),
    )
}

@Composable
private fun ProductCard(product: Product, onClick: (Int) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.clickable { onClick(product.id) },
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
        )
        Column(Modifier.padding(12.dp)) {
            Text(product.title, maxLines = 2, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            Text("£%.2f".format(product.price), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text(stringResource(product.category.titleRes), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
