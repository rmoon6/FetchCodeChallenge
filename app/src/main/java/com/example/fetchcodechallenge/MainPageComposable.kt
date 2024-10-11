package com.example.fetchcodechallenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fetchcodechallenge.theme.FetchCodeChallengeTheme

@Composable
fun GroupedListView(modifier: Modifier, items: List<FetchListItem>) {
    val groupedItems = items.groupBy { it.listId }
    LazyColumn(
        modifier = modifier
    ) {

        groupedItems.forEach { (listId, itemsInGroup) ->
            item {
                GroupHeader(
                    modifier = Modifier.fillMaxWidth(),
                    listId = listId
                )
            }

            items(itemsInGroup) { item ->
                ListItemView(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
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
private fun GroupHeader(
    modifier: Modifier,
    listId: Int
) {
    Text(
        modifier = modifier,
        text = "List ID: $listId",
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun ListItemView(modifier: Modifier, item: FetchListItem) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Item ID: ${item.id}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = item.name ?: "TODO STOPSHIP get rid of this!!",
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
private fun GroupedListViewPreview() {
    FetchCodeChallengeTheme {
        GroupedListView(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            items = sampleItems
        )
    }
}
