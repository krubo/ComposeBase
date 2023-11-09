package com.krubo.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieDrawable
import com.krubo.compose.ui.common.SafeExpandableColumn
import com.krubo.compose.ui.common.SafeExpandableTitleData
import com.krubo.compose.ui.common.SafeFlowColumn
import com.krubo.compose.ui.common.SafeLottieAnimationView
import com.krubo.compose.ui.theme.ComposeBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        testSafeLottieAnimationView()
                        testSafeFlowColumn()
                        testSafeExpandableColumn()
                    }
                }
            }
        }
    }
}

data class ExpandableItem(
    override val key: Any,
    override val data: String,
    override val items: List<String>
) : SafeExpandableTitleData<String, String>

@Composable
fun testSafeExpandableColumn() {
    val datas = arrayListOf<ExpandableItem>(
        ExpandableItem(
            0,
            "Title",
            arrayListOf(
                "Item", "Item", "Item", "Item", "Item", "Item", "Item", "Item", "Item"
            )
        ),
        ExpandableItem(
            1,
            "Title",
            arrayListOf(
                "Item", "Item", "Item", "Item"
            )
        ),
        ExpandableItem(
            2,
            "Title",
            arrayListOf(
                "Item", "Item", "Item", "Item", "Item", "Item"
            )
        ),
        ExpandableItem(
            3,
            "Title",
            arrayListOf()
        ),
        ExpandableItem(
            4,
            "Title",
            arrayListOf(
                "Item", "Item", "Item", "Item", "Item", "Item", "Item", "Item", "Item"
            )
        ),
    )
    Box {
        SafeExpandableColumn(
            datas = datas,
            groupItemView = { item, position, isExpandable, hasChild ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.background(Color.Gray)
                ) {
                    Text(
                        text = "$position  $item",
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp)
                    )
                    if (hasChild) {
                        Icon(
                            imageVector = if (isExpandable) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }

            },
            childItemView = { item, groupPosition, childPosition ->
                Text(
                    text = "$childPosition  $item",
                    modifier = Modifier.padding(start = 20.dp)
                )
            })
    }
}

@Composable
fun testSafeLottieAnimationView() {
    SafeLottieAnimationView(
        modifier = Modifier.size(200.dp),
        repeatCount = LottieDrawable.INFINITE,
        rawId = R.raw.antivirus,
        autoPlay = true
    )
}

@Composable
fun testSafeFlowColumn() {
    val abc = mutableListOf<String>(
        "aaaafasdfffffasdfasdffasdfasdfasdfcccfasdfasdfasdfcccfasdfasdfasdfcccfasdfasdfasdfcccasdfasdfccccczzz",
        "asd",
        "ffe",
        "sefasdxzefzxvzxcvzxcvsvzdvzvcz",
        "aaafiemnjji",
        "afasdfasfaafiemnjji",
        "aeraafiemnjji",
        "cvzfiemnjji",
        "eriemnjji",
        "erataaafweriemnjji",
        "eranjji",
        "vzcaaafiemnjji",
        "afiemnjji",
        "jji",
        "cvzfiemnjji",
        "erataaafweriemnjji",
        "erataaafweriemnjji",
        "erataanjji",
        "vzcaaafiemnjji",
        "afiemnjji",
        "cvzfiemnjji",
        "erataaafweriemnjji",
        "erataaafweriemnjji",
        "sefasdxzefxcvsvzdvzvcz",
        "aaafiemnjji",
        "afasdfasfaafiemnjji",
        "aeraafiemnjji",
        "asd",
        "ffe",
        "sefasdxzefzxvzxcvzxcvsvzdvzvcz",
        "aaafiemnjji",
        "afasdfasfaafiemnjji",
        "aeraafiemnjji",
        "cvzfiemnjji",
        "eriemnjji",
        "erataaafweriemnjji",
        "eranjji",
        "vzcaaafiemnjji",
        "afiemnjji",
        "jji",
        "cvzfiemnjji",
        "erataaafweriemnjji",
        "erataaafweriemnjji",
        "erataanjji",
        "vzcaaafiemnjji",
        "afiemnjji",
        "cvzfiemnjji",
        "erataaafweriemnjji",
        "erataaafweriemnjji",
        "sefasdxzefxcvsvzdvzvcz",
        "aaafiemnjji",
        "afasdfasfaafiemnjji",
        "aeraafiemnjji",
    )
    SafeFlowColumn(datas = abc, modifier = Modifier.height(300.dp)) { position, item ->
        Text(
            text = "$position  $item", modifier = Modifier
                .padding(4.dp)
                .background(Color.Gray)
                .padding(10.dp)
                .background(Color.Red)
        )
    }
}