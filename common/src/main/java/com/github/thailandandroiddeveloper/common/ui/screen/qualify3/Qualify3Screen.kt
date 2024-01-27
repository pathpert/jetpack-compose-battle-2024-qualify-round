package com.github.thailandandroiddeveloper.common.ui.screen.qualify3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.thailandandroiddeveloper.common.R
import com.github.thailandandroiddeveloper.common.ui.preview.Pixel7
import com.github.thailandandroiddeveloper.common.ui.theme.AppTheme
import com.github.thailandandroiddeveloper.common.ui.theme.LightColors.SurfaceContainerLowest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Qualify3Screen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val imageList = listOf(
        R.drawable.img_qualify_3_photo_1,
        R.drawable.img_qualify_3_photo_2,
        R.drawable.img_qualify_3_photo_3
    )
    val descriptionTexts = stringArrayResource(id = R.array.description_texts)
    val selectedIndex by remember { mutableIntStateOf(0) }
    val tagSelectedList  = listOf(0)

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                title = {
                    Text(
                        text = "John Doe",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_qualify_3_back),
                            contentDescription = "drawer menu",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                actions = {
                    Row {
                        IconButton(
                            onClick = { },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_qualify_3_copy),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        IconButton(
                            onClick = { },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_qualify_3_calendar),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        IconButton(
                            onClick = { },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_qualify_3_menu),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier
                .background(SurfaceContainerLowest)
                .padding(paddingValues)
                .padding(top = 16.dp)
                .fillMaxSize()
            ) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(3) {
                        CarouselImageView(
                            image = painterResource(id = imageList[it]),
                            selected = selectedIndex == it
                        )
                    }
                }
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(4) {
                        TagView(label = "Tag ${it+1}", selected = tagSelectedList.contains(it))
                    }
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(4) {
                        InfoCard(description = descriptionTexts[it])
                    }
                }
            }
        }
    )
}

@Composable
fun CarouselImageView(image: Painter, selected: Boolean = false) {
    Card(
        Modifier
            .width(160.dp)
            .height(320.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(2.dp, if(selected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.primaryContainer),
    ) {
        Image(
            painter = image,
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagView(label: String, selected: Boolean) {
    InputChip(
        selected = selected,
        onClick = { /*TODO*/ },
        border = InputChipDefaults.inputChipBorder(
            borderColor = MaterialTheme.colorScheme.outline,
            selectedBorderColor = MaterialTheme.colorScheme.primary,
            selectedBorderWidth = 1.dp
        ),
        colors = InputChipDefaults.inputChipColors(
            selectedLabelColor = MaterialTheme.colorScheme.primary,
            labelColor = MaterialTheme.colorScheme.outline,
            containerColor = Color.Transparent,
            selectedContainerColor = Color.Transparent
        ),
        shape = MaterialTheme.shapes.small,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
        }
    )
}

@Composable
fun InfoCard(description: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(96.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.img_qualify_3_sender),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(48.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Lorem Ipsum",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// region Read-only because we use this to process your score.
@Composable
@Preview(group = Pixel7.name, device = Pixel7.spec, showBackground = true)
fun Qualify3ScreenPreview() = AppTheme {
    Qualify3Screen()
}
// endregion