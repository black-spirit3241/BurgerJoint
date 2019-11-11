package com.padc.burgershop.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_burger_detail)
        setUpPresenter()
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
