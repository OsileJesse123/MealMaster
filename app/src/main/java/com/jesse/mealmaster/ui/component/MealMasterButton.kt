package com.jesse.mealmaster.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MealMasterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean = false,
    text: String
) {

    val containerColor by animateColorAsState(
        targetValue = if (!isLoading) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
        label = "button container color",
        animationSpec = tween(600)
    )

    Button(
        onClick = onClick,
        enabled = !isLoading,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        contentPadding = PaddingValues(vertical = 15.dp)
    ) {
        if (isLoading){
            CircularProgressIndicator(
                modifier = Modifier.wrapContentSize(),
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else{
            Text(text = text)
        }
    }
}