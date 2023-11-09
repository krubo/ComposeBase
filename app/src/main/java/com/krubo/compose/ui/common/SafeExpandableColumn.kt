package com.krubo.compose.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

interface SafeExpandableTitleData<M, N> {
    val key: Any
    val data: M
    val items: List<N>
}

@Composable
fun <M, N, V : SafeExpandableTitleData<M, N>> SafeExpandableColumn(
    datas: List<V>,
    groupItemView: @Composable (item: M, position: Int, isExpandable: Boolean, hasChild: Boolean) -> Unit,
    childItemView: @Composable (item: N, groupPosition: Int, childPosition: Int) -> Unit
) {
    LazyColumn(content = {
        itemsIndexed(datas, key = { _, item -> item.key }) { position, item ->
            SafeExpandableItemContent(item, position, groupItemView, childItemView)
        }
    })
}

@Composable
private fun <M, N, V : SafeExpandableTitleData<M, N>> SafeExpandableItemContent(
    groupItem: V, groupPosition: Int,
    groupItemView: @Composable (item: M, position: Int, isExpandable: Boolean, hasChild: Boolean) -> Unit,
    childItemView: @Composable (item: N, groupPosition: Int, childPosition: Int) -> Unit
) {
    var isExpandable by rememberSaveable {
        mutableStateOf(true)
    }
    Column {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpandable = !isExpandable }) {
            groupItemView(groupItem.data, groupPosition, isExpandable, groupItem.items.isNotEmpty())
        }
        if (isExpandable) {
            Column(modifier = Modifier.fillMaxWidth()) {
                groupItem.items.forEachIndexed { childPosition, item ->
                    childItemView(item, groupPosition, childPosition)
                }
            }
        }
    }
}