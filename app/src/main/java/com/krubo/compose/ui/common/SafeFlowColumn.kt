package com.krubo.compose.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import kotlin.math.max
import kotlin.math.min

private data class FlowPosition(val x: Int, val y: Int)

@Composable
fun <T> SafeFlowColumn(
    modifier: Modifier = Modifier,
    datas: List<T>,
    itemView: @Composable (position: Int, item: T) -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Layout(
            content = {
                datas.forEachIndexed { position, item -> itemView(position, item) }
            }) { measurables, constraints ->
            val maxWidth = constraints.maxWidth
            val itemPosition = arrayListOf<FlowPosition>()
            var contentHeight = 0
            var x = 0
            var y = 0
            var lastRowHeight = 0
            val placeables = measurables.mapIndexed { index, measurable ->
                val placeable = measurable.measure(constraints)
                val itemWidth = placeable.width
                val itemHeight = placeable.height
                if (x == 0) {
                    itemPosition.add(FlowPosition(x = x, y = y))
                    if (itemWidth > maxWidth) {
                        y += itemHeight
                        lastRowHeight = itemHeight
                    } else {
                        x += itemWidth
                        lastRowHeight = max(lastRowHeight, itemHeight)
                    }
                } else {
                    if (x + itemWidth > maxWidth) {
                        itemPosition.add(FlowPosition(x = 0, y = y + lastRowHeight))
                        x = itemWidth
                        y += lastRowHeight
                        lastRowHeight = itemHeight
                    } else {
                        itemPosition.add(FlowPosition(x = x, y = y))
                        x += itemWidth
                        lastRowHeight = max(lastRowHeight, itemHeight)
                    }
                }
                if (index == datas.size - 1) {
                    contentHeight = y + lastRowHeight
                }
                placeable
            }
            layout(maxWidth, min(contentHeight, constraints.maxHeight)) {

                placeables.forEachIndexed { index, placeable ->
                    val position = itemPosition[index]
                    placeable.placeRelative(x = position.x, y = position.y)
                }
            }
        }
    }
}