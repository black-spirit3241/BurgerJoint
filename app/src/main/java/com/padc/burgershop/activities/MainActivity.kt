package com.padc.burgershop.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.padc.burgershop.R
import com.padc.burgershop.adapters.BurgerAdapter
import com.padc.burgershop.data.vos.BurgerVO
import com.padc.burgershop.mvp.presenters.MainPresenter
import com.padc.burgershop.mvp.presenters.impl.MainPresenterImpl
import com.padc.burgershop.mvp.views.MainView
import kotlinx.android.synthetic.main.activity_burger_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.view_item_burger.*

class MainActivity :BaseActivity(), MainView {

    private lateinit var mBurgerAdapter: BurgerAdapter
    private lateinit var mPresenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        setUpPresenter()
        setUpRecycler()
        setUpListeners()
        mPresenter.onUIReady(this)
    }

    private fun setUpPresenter(){
        mPresenter=getPresenter<MainPresenterImpl,MainView>()
    }

    private fun setUpListeners(){
        ivCart.setOnClickListener{
            mPresenter.onTapCart()
        }
    }

    private fun setUpRecycler(){
        mBurgerAdapter= BurgerAdapter(mPresenter)
        rvBurgerList.adapter=mBurgerAdapter
        rvBurgerList.layoutManager = GridLayoutManager(applicationContext, 1)
    }

    override fun displayBurgerList(burgerList: List<BurgerVO>) {
        mBurgerAdapter.setNewData(burgerList)
    }

    override fun navigateToDetailScreen(burgerId: Int,burgerImageView: ImageView,burgerName : TextView) {
        val imagePair=Pair.create(burgerImageView as View,"tBurgerImage")
        val namePair=Pair.create(burgerName as View,"tBurgerName")
       val sceneTransition= ActivityOptionsCompat.makeSceneTransitionAnimation(this,imagePair,namePair)
        startActivity(BurgerDetailActivity.newIntent(this,burgerId),sceneTransition.toBundle())
    }

    override fun navigateToCartScreen() {
        startActivity(CartActivity.newIntent(this))
    }

    override fun addBurgerToCart(burger: BurgerVO,burgerImageView: ImageView) {
        val burgerPosition=getViewLocation(burgerImageView)
        val cartPosition=getViewLocation(ivCart)

        val viewToAnimate=setUpImageViewToAnimate(burger,burgerImageView,80)
        flRoot.addView(viewToAnimate)

        val imageSie =80

        val xAnimator=ObjectAnimator.ofFloat(viewToAnimate,View.TRANSLATION_X,
            burgerPosition[0].toFloat(),
            (cartPosition[0]-(imageSie*2)).toFloat())
        xAnimator.duration=500

        val yAnimator=ObjectAnimator.ofFloat(viewToAnimate,View.TRANSLATION_Y,
            burgerPosition[1].toFloat(),
            (cartPosition[1]-(imageSie*4)).toFloat())
        yAnimator.duration=500

        val alphaAnimator=ObjectAnimator.ofFloat(viewToAnimate,View.ALPHA,
            0f,1f)
        alphaAnimator.duration=500

        val xScaleAnimator=ObjectAnimator.ofFloat(viewToAnimate,View.SCALE_X,
            1f,0.25f)

        val yScaleAnimator=ObjectAnimator.ofFloat(viewToAnimate,View.SCALE_Y,
            1f,0.25f)

        AnimatorSet().apply {
            play(xAnimator).with(yAnimator).with(alphaAnimator)
                .with(xScaleAnimator).with(yScaleAnimator)
                addListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        flRoot.removeView(viewToAnimate)
                    }
                })
            start()
        }
    }

    override fun displayCountInCart(burgerCount: Int) {
        tvCartCount.text = burgerCount.toString()
    }

    //Methods
    private fun getViewLocation(view : View):IntArray{
        val locationOnScreen= intArrayOf(0,0)
        view?.getLocationOnScreen(locationOnScreen)
        return locationOnScreen
    }


    private fun setUpImageViewToAnimate(burger:BurgerVO,burgerImageView: ImageView,imageSize : Int):ImageView{
        val viewToAnimate=ImageView(this)
        Glide.with(this)
            .load(burger.burgerImageUrl)
            .into(viewToAnimate)

        var layoutParams=burgerImageView.layoutParams
        layoutParams.width=burgerImageView.width
        layoutParams.height=burgerImageView.height

        viewToAnimate.layoutParams=layoutParams
        viewToAnimate.alpha=0f
        return viewToAnimate
    }

}

