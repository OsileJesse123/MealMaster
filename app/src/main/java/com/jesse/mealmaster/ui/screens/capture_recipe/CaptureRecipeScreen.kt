package com.jesse.mealmaster.ui.screens.capture_recipe

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jesse.mealmaster.R
import com.jesse.mealmaster.data.model.Recipe
import com.jesse.mealmaster.ui.component.MealMasterButton
import com.jesse.mealmaster.ui.shared.SharedViewModel
import com.jesse.mealmaster.ui.theme.MealMasterTheme

@Composable
fun CaptureRecipeRoute(
    onNavigateToRecipeDetailsScreen: () -> Unit,
    viewModel: CaptureScreenViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {
    
    val captureRecipeUiState by viewModel.captureScreenUiState.collectAsStateWithLifecycle()

    val cameraLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()){
            bitmap ->
        bitmap?.let {
            viewModel.identifyRecipe(it)
        }
    }
    CaptureRecipeScreen(
        onCaptureRecipe = {cameraLauncher.launch()},
        onNavigateToRecipeDetailsScreen = onNavigateToRecipeDetailsScreen,
        uiState = captureRecipeUiState,
        onUpdateRecipe = {
            recipe ->
            // TODO 008: Initiate Update recipe

        }
    )
    captureRecipeUiState.errorMessage?.let {
            errorMessage ->
        Toast.makeText(LocalContext.current, stringResource(id = errorMessage), Toast.LENGTH_SHORT).show()
        viewModel.onErrorMessageShown()
    }

}

@Composable
fun CaptureRecipeScreen(
    onCaptureRecipe: () -> Unit,
    onNavigateToRecipeDetailsScreen: () -> Unit,
    onUpdateRecipe: (Recipe) -> Unit,
    uiState: CaptureRecipeUiState
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 30.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // So long as the recipe object is not null and the recipe name is not empty, let this text
        // be visible
        AnimatedVisibility(visible = uiState.recipe != null && uiState.recipe.name != "No Food") {
            uiState.recipe?.let {
                recipe ->
                Text(
                    text = stringResource(id = R.string.the_meal_here_is, recipe.name),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedVisibility(visible = uiState.bitmap != null,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            Image(
                bitmap = uiState.bitmap!!.asImageBitmap(),
                contentDescription = stringResource(id = R.string.recipe_image),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        MealMasterButton(
            onClick = onCaptureRecipe,
            isLoading = uiState.isLoading,
            text = stringResource(id = R.string.take_a_picture),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedVisibility(visible = uiState.successful) {
            MealMasterButton(
                onClick = {
                    // TODO 007: Update recipe and navigate to details screen

                },
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.tell_me_more_about_it)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CaptureRecipeScreenPreview() {
    MealMasterTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            CaptureRecipeScreen(
                onCaptureRecipe = {},
                onNavigateToRecipeDetailsScreen = {},
                uiState = CaptureRecipeUiState(),
                onUpdateRecipe = {}
            )
        }
    }
}