package com.jesse.mealmaster.ui.screens.recipe_detail.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesse.mealmaster.R
import com.jesse.mealmaster.ui.theme.MealMasterTheme

@Composable
fun StepsScreen(
    steps: List<String>
) {
    if (steps.isEmpty()){

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.no_steps),
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 18.sp
                )
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
            flingBehavior = ScrollableDefaults.flingBehavior()
        ){
            items(steps){
                step ->
                Text(
                    text = "${steps.indexOf(step) + 1}. $step",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(vertical = 15.dp, horizontal = 10.dp),

                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(height = 10.dp))

            }
        }
    }
}


@Preview
@Composable
fun StepsScreenPreview() {
    MealMasterTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            StepsScreen(steps = listOf(
                "boil water",
                "peel the yam",
                "break the eggs"
            ))
        }
    }
}