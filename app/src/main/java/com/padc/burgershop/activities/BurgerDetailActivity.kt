package com.padc.burgershop.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.padc.burgershop.R
import com.padc.burgershop.data.vos.BurgerVO
import com.padc.burgershop.mvp.presenters.BurgerDetailPreserter
import com.padc.burgershop.mvp.presenters.impl.BurgerDetailPresenterImpl
import com.padc.burgershop.mvp.views.BurgerDetailView
import kotlinx.android.synthetic.main.activity_burger_detail.*

class BurgerDetailActivity : BaseActivity() ,BurgerDetailView{


    companion object{
        private const val EXTRA_BURGER_ID = "Burger Id Extra"

        fun newIntent(context: Context, burgerId : Int): Intent{
            val intent = Intent(context, BurgerDetailActivity::class.java)
            intent.putExtra(EXTRA_BURGER_ID,burgerId)
            return intent
        }
    }

    private lateinit var mPresenter : BurgerDetailPreserter
    private var isFavourite=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_burger_detail)
        setUpPresenter()
        setUpListeners()

        val burgerId = intent.getIntExtra(EXTRA_BURGER_ID, 0)
        mPresenter.onBurgerDetailsUiReady(this, burgerId)
    }



    private fun setUpListeners(){
        ivBurger.setOnClickListener{
            val animator=AnimationUtils.loadAnimation(this,R.anim.rotate)
            ivBurger.startAnimation(animator)
        }

        fab.setOnClickListener{
           val animator= AnimationUtils.loadAnimation(this,R.anim.rotate)
            animator.repeatCount=3
            animator.duration=900
            fab.startAnimation(animator)
        }

        btnFavourite.setOnClickListener{
            if(!isFavourite){
                btnFavourite.speed=1.0f
                btnFavourite.playAnimation()
                isFavourite=true
            }else{
                btnFavourite.speed=-4.0f
                btnFavourite.playAnimation()
                isFavourite=false
            }

        }
    }

    override fun displayBurgerDetails(burger: BurgerVO) {
        tvBurgerName.text = burger.burgerName
        tvDescription.text = burger.burgerDescription
        Glide.with(ivBurger)
            .load(burger.burgerImageUrl)
            .into(ivBurger)
    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<BurgerDetailPresenterImpl,BurgerDetailView>()
    }
}
