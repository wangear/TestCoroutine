package com.toy.loginwork.util

import android.animation.ObjectAnimator
import android.view.View


class ScaleInterfaceImpl : ScaleInterface {
    override fun startScaleAnimation(view: View) {
        val scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 1.2f)
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 1.2f)
        scaleDownX.duration = 150
        scaleDownY.duration = 150

        scaleDownX.start()
        scaleDownY.start()
    }

    override fun cancelScaleAnimation(view: View) {
        val scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f)
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f)
        scaleDownX.duration = 150
        scaleDownY.duration = 150

        scaleDownX.start()
        scaleDownY.start()
    }
}