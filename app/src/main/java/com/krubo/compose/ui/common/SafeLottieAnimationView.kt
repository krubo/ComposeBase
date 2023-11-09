package com.krubo.compose.ui.common

import android.text.TextUtils
import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

@Composable
fun SafeLottieAnimationView(
    modifier: Modifier = Modifier,
    repeatMode: Int = LottieDrawable.RESTART,
    repeatCount: Int = 0,
    autoPlay: Boolean = false,
    @RawRes rawId: Int = 0,
    assetName: String? = null,
    initView: LottieAnimationView.() -> Unit = {}
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LottieAnimationView(context).apply {
                this.repeatMode = repeatMode
                this.repeatCount = repeatCount
                if (rawId != 0) {
                    setAnimation(rawId)
                } else if (!TextUtils.isEmpty(assetName)) {
                    setAnimation(assetName)
                }
                initView()
                if (autoPlay) {
                    playAnimation()
                }
            }
        })
}