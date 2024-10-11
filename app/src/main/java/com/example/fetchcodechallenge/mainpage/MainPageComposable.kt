package com.example.fetchcodechallenge.mainpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchcodechallenge.api.FetchListItem
import com.example.fetchcodechallenge.mainpage.internal.MainPageState
import com.example.fetchcodechallenge.mainpage.internal.MainPageState.Loading
import com.example.fetchcodechallenge.mainpage.internal.MainPageState.NetworkError
import com.example.fetchcodechallenge.mainpage.internal.MainPageState.WithItems
import com.example.fetchcodechallenge.mainpage.internal.MainPageViewModel
import com.example.fetchcodechallenge.theme.FetchCodeChallengeTheme

@Composable
fun MainPage(modifier: Modifier) {
    val viewModel: MainPageViewModel = viewModel(factory = MainPageViewModel.CreationFactory)
    val state = viewModel.state.collectAsState().value
    MainPage(
        modifier = modifier.padding(8.dp
        ),
        state = state,
        onNetworkErrorRetryClicked = { viewModel.onNetworkErrorRetry() }
    )
}

@Composable
private fun MainPage(
    modifier: Modifier,
    state: MainPageState,
    onNetworkErrorRetryClicked: () -> Unit
) {
    when (state) {
        Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        NetworkError -> NetworkErrorScreen(
            modifier = modifier.fillMaxSize(),
            onRetryClicked = onNetworkErrorRetryClicked
        )
        is WithItems -> FetchItemGroupedList(modifier = modifier.fillMaxSize(), items = state.items)
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun NetworkErrorScreen(
    modifier: Modifier,
    onRetryClicked: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Network Error",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetryClicked) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
private fun FetchItemGroupedList(modifier: Modifier, items: List<FetchListItem>) {
    val groupedItems = remember(items) { items.groupBy { it.listId } }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        groupedItems.forEach { (listId, itemsInGroup) ->
            item {
                FetchItemListHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    listId = listId
                )
            }

            items(itemsInGroup) { item ->
                FetchItemView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    item = item
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun FetchItemListHeader(
    modifier: Modifier,
    listId: Int
) {
    Text(
        modifier = modifier,
        text = "List $listId",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun FetchItemView(modifier: Modifier, item: FetchListItem) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "•",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = item.name ?: "",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////
//////////////// PREVIEW STUFF
////////////////////////////////////////////////////////////////////////////////////////////////

private val sampleItems = listOf(
    FetchListItem(1, 1, "Apple"),
    FetchListItem(2, 1, "Banana"),
    FetchListItem(3, 2, "Carrot"),
    FetchListItem(4, 2, "Broccoli"),
    FetchListItem(5, 3, "Chicken")
)

@Composable
@Preview
private fun FetchItemGroupedListPreview() {
    FetchCodeChallengeTheme {
        FetchItemGroupedList(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            items = sampleItems
        )
    }
}
