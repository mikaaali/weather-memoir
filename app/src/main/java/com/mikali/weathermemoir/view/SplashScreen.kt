package com.mikali.weathermemoir.view

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mikali.weathermemoir.R
import com.mikali.weathermemoir.navigation.NavigationScreens
import com.mikali.weathermemoir.view.theme.Cyan
import com.mikali.weathermemoir.view.theme.Green
import com.mikali.weathermemoir.view.theme.LightGreen
import com.mikali.weathermemoir.view.theme.Teal
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1200,
                    easing = {
                        OvershootInterpolator().getInterpolation(it)
                    }
                )
            )
            delay(1500)
            navController.navigate(
                route = NavigationScreens.LOGIN.name
            )
        }
    )

    Box(modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .size(size = 380.dp)
                .scale(scale = scale.value),
            shape = CircleShape,
            color = Color.White,
            border = BorderStroke(
                width = 16.dp,
                brush = Brush.linearGradient(listOf(Cyan, Teal, Green, LightGreen))
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash),
                contentScale = ContentScale.Inside,
                contentDescription = "splash logo"
            )
        }
    }
}
