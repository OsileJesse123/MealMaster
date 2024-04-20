package com.jesse.mealmaster.ui.screens.recipe_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jesse.mealmaster.R
import com.jesse.mealmaster.data.model.Recipe
import com.jesse.mealmaster.ui.screens.recipe_detail.ingredients.IngredientsScreen
import com.jesse.mealmaster.ui.screens.recipe_detail.steps.StepsScreen
import com.jesse.mealmaster.ui.shared.SharedViewModel
import com.jesse.mealmaster.ui.theme.MealMasterTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailRoute(
    onNavigateBack: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    val recipe by sharedViewModel.recipe

    val tabItems = listOf(stringResource(id = R.string.ingredients), stringResource(id = R.string.steps))

    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabItems.size
    }
    LaunchedEffect(selectedTabIndex){
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress){
        if (!pagerState.isScrollInProgress){
            selectedTabIndex = pagerState.currentPage
        }
    }
    recipe?.let {
        recipe ->
        DetailScreen(
            tabItems = tabItems,
            onNavigateBack = onNavigateBack,
            recipe = recipe,
            selectedTabIndex = selectedTabIndex,
            pagerState = pagerState,
            updateSelectedIndex = {
                    index -> selectedTabIndex = index
            }
        )
    } ?: Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    tabItems: List<String>,
    onNavigateBack: () -> Unit,
    recipe: Recipe,
    selectedTabIndex: Int,
    pagerState: PagerState,
    updateSelectedIndex: (Int) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "arrow back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = recipe.name,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
        TabRow(
            selectedTabIndex = selectedTabIndex
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {updateSelectedIndex(index)},
                    text = {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 13.sp
                            )
                        )
                    },
                    unselectedContentColor = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {index ->
            when(index){
                0 -> IngredientsScreen(ingredients = recipe.ingredients)
                1 -> StepsScreen(steps = recipe.steps)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MealMasterTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            DetailScreen(
                tabItems = listOf("Ingredients", "Steps"),
                onNavigateBack = {  },
                recipe = Recipe(name = "Rice",
                    ingredients = listOf("Rice", "Water", "Salt", "Onions"),
                    steps = listOf("Wash rice", "Boil water", "Boil rice", "Add salt"),
                    status = 1),
                selectedTabIndex = 0,
                pagerState = rememberPagerState {
                    2
                },
                updateSelectedIndex = {
                        index ->
                }
            )
        }
    }
}