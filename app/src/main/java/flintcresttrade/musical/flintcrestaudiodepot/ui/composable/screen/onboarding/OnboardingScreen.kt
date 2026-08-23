package flintcresttrade.musical.flintcrestaudiodepot.ui.composable.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import flintcresttrade.musical.flintcrestaudiodepot.R
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.WHDBNOnboardingVM
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private data class Page(val title: Int, val description: Int, val image: Int)

private val pages = listOf(
    Page(R.string.whdbn_page_1_title, R.string.whdbn_page_1_description, R.drawable.onboarding_guitar),
    Page(R.string.whdbn_page_2_title, R.string.whdbn_page_2_description, R.drawable.onboarding_studio),
    Page(R.string.whdbn_page_3_title, R.string.whdbn_page_3_description, R.drawable.onboarding_headphones),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: WHDBNOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val completed by viewModel.onboardingSetState.collectAsState()
    val pagerState = rememberPagerState { pages.size }
    val scope = rememberCoroutineScope()

    LaunchedEffect(completed) {
        if (completed) onNavigateToHomeScreen()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
        ) { index ->
            val page = pages[index]
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(page.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(180.dp, 140.dp)
                        .clip(RoundedCornerShape(16.dp)),
                )
                Spacer(Modifier.height(30.dp))
                Text(stringResource(page.title), style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(12.dp))
                Text(
                    text = stringResource(page.description),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            pages.indices.forEach { index ->
                Spacer(
                    Modifier
                        .size(if (index == pagerState.currentPage) 12.dp else 8.dp)
                        .clip(CircleShape)
                        .background(if (index == pagerState.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline),
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                if (pagerState.currentPage == pages.lastIndex) {
                    viewModel.setOnboarded()
                } else {
                    scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(if (pagerState.currentPage == pages.lastIndex) R.string.whdbn_start_button_title else R.string.whdbn_next_button_title))
        }
    }
}
