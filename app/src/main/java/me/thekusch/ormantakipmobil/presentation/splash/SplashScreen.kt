package me.thekusch.ormantakipmobil.presentation.splash

import me.thekusch.ormantakipmobil.ui.theme.OrmanTakipMobilTheme
import me.thekusch.ormantakipmobil.util.Screen
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import me.thekusch.ormantakipmobil.R
import me.thekusch.ormantakipmobil.ui.theme.LightBrown

@Composable
fun AnimatedSplashScreen(
    navController: NavController
) {
    var isAnimationStart by remember {
        mutableStateOf(false)
    }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (isAnimationStart) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )
    LaunchedEffect(key1 = true) {
        isAnimationStart = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }
    SplashScreen(alphaVal = alphaAnimation.value)
}

@Composable
fun SplashScreen(alphaVal: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.alpha(alpha = alphaVal)
        ) {
            Surface(
                shape = CircleShape,
                color = Color.White,
                modifier = Modifier.size(150.dp, 150.dp),
                elevation = 5.dp
            ) {
                Image(
                    painterResource(id = R.drawable.logo),
                    contentDescription = "App Icon",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(10.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.splash_title),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    OrmanTakipMobilTheme {
        SplashScreen(alphaVal = 1f)
    }
}