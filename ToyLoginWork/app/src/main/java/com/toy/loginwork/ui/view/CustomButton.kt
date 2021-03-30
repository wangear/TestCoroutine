package com.toy.loginwork.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import com.toy.loginwork.util.ScaleInterface
import com.toy.loginwork.util.ScaleInterfaceImpl

class CustomButton : AppCompatButton{
    var scaleInterface : ScaleInterface = ScaleInterfaceImpl()

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet?) : super(ctx, attrs)
    constructor(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        ctx,
        attrs,
        defStyleAttr
    )

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when(it.action){
                MotionEvent.ACTION_DOWN->{
                    scaleInterface.startScaleAnimation(this)
                }
                MotionEvent.ACTION_UP->{
                    scaleInterface.cancelScaleAnimation(this)
                }
            }
        }

        return super.onTouchEvent(event)
    }

}