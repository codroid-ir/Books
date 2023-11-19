package ir.codroid.books.presentation.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ir.codroid.books.R
import ir.codroid.books.presentation.ui.theme.Purple80

@Composable
fun DefaultError(errorMessage: String) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.error_anim)
    )
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyMedium,
            color = Purple80,
        )
}