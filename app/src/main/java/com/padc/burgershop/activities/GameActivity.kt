package com.padc.burgershop.activities

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.solver.widgets.Rectangle
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padc.burgershop.R
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    companion object{
         fun newIntent(context:Context) : Intent {
            return Intent(context,GameActivity::class.java)
        }
    }

    //Spring Animation
    private val springForce : SpringForce by lazy {
        SpringForce(0f).apply {
            stiffness=SpringForce.STIFFNESS_HIGH
            dampingRatio=SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        }
    }

    private val springAnimationX : SpringAnimation by lazy {
        SpringAnimation(ivGameBurger,DynamicAnimation.TRANSLATION_Y).setSpring(springForce)
    }

    private val springAnimationY : SpringAnimation by lazy{
        SpringAnimation(ivGameBurger,DynamicAnimation.TRANSLATION_Y).setSpring(springForce)
    }

    private var xPositionDiff=0f
    private var yPositionDiff=0f

    //Fling Animation
    private  val burgerFlingAnimationX : FlingAnimation by lazy {
        FlingAnimation(ivGameBurger,DynamicAnimation.X).setFriction(1f)
    }


    // Fling Animation
    private  val burgerFlingAnimationY : FlingAnimation by lazy {
        FlingAnimation(ivGameBurger,DynamicAnimation.Y).setFriction(1f)
    }

    private val burgerGestureListener= object : GestureDetector.SimpleOnGestureListener(){
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            burgerFlingAnimationX.setStartVelocity(velocityX)
            burgerFlingAnimationY.setStartVelocity(velocityY)

            burgerFlingAnimationY.start()
            burgerFlingAnimationX.start()

            return true
        }
    }

    private  val burgerGestureDetector : GestureDetector by lazy {
        GestureDetector(this,burgerGestureListener)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        Glide.with(this)
            .load("https://vignette.wikia.nocookie.net/simpsons-restaurants/images/2/20/Spicy_Clucker.png/revision/latest?cb=20131125185837")
            .into(ivGameBurger)
        setUpTouchListener()
        setUpBlockAnimation()
        setUpTreeObserver()
        setUpAnimationEndListener()
    }

    private fun setUpBlockAnimation(){
        val displayMetrics=DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width=displayMetrics.widthPixels

        ObjectAnimator.ofFloat(
            block,
            View.TRANSLATION_X,
            0f,
            width.toFloat() - resources.getDimension(R.dimen.block_width)
        )
            .apply {
                interpolator=AccelerateDecelerateInterpolator()
                repeatCount=ObjectAnimator.INFINITE
                repeatMode=ObjectAnimator.REVERSE
                duration=300L
                start()
            }
    }


    private fun setUpTreeObserver()
    {
        ivGameBurger.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                val width=displayMetrics.widthPixels
                val height=displayMetrics.heightPixels

                burgerFlingAnimationX.setMinValue(0f)
                    .setMaxValue((width-ivGameBurger.width).toFloat())

                burgerFlingAnimationY.setMinValue(0f)
                    .setMaxValue((height - (ivGameBurger.height)).toFloat())

                ivGameBurger.viewTreeObserver.removeOnGlobalLayoutListener { this }
            }
        })
    }

    private fun setUpAnimationEndListener(){
        burgerFlingAnimationY.addEndListener{
                _,_,_,_ ->
            if(areViewsOverlapping(block,ivGameBurger)){
                Snackbar.make(window.decorView,"You Win!!",Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun areViewsOverlapping(v1 : View, v2 : View) : Boolean{
        val rect1= Rect()
        v1.getHitRect(rect1)

        var rect2=Rect()
        v2.getHitRect(rect2)

        return Rect.intersects(rect1,rect2)
    }


    //used for springAnimation
//    private fun setUpTouchListener(){
//        ivGameBurger.setOnTouchListener {
//                view, motionEvent ->
//            when(motionEvent.action){
//                //hold view
//                MotionEvent.ACTION_DOWN -> {
//                    springAnimationX.cancel()
//                    springAnimationY.cancel()
//                    xPositionDiff=motionEvent.rawX-view.x
//                    yPositionDiff=motionEvent.rawY-view.y
//                }
//
//                MotionEvent.ACTION_MOVE -> {
//                    ivGameBurger.x=motionEvent.rawX-xPositionDiff
//                    ivGameBurger.y=motionEvent.rawY-yPositionDiff
//                }
//
//                //release view
//                MotionEvent.ACTION_UP -> {
//                    springAnimationX.start()
//                    springAnimationX.start()
//                }
//            }
//          true
//        }
//    }

    private fun setUpTouchListener(){
        ivGameBurger.setOnTouchListener { _, motionEvent ->
            burgerGestureDetector.onTouchEvent(motionEvent)
        }
    }
}
