package ir.codroid.books.presentation.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ir.codroid.books.R

@Composable
fun DefaultLoading(isSystemDark: Boolean, showLoading: Boolean) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            if (isSystemDark) R.raw.dark_loading else R.raw.light_loading
        )
    )
    if (showLoading)
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
}