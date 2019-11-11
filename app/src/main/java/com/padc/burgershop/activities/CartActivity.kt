package com.padc.burgershop.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.padc.burgershop.R
import com.padc.burgershop.adapters.CartAdapter
import com.padc.burgershop.data.vos.BurgerVO
import com.padc.burgershop.mvp.presenters.CartPresenter
import com.padc.burgershop.mvp.presenters.impl.CartPresenterImpl
import com.padc.burgershop.mvp.views.CartView
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : BaseActivity() , CartView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CartActivity::class.java)
        }
    }

    private lateinit var mBurgerAdapter: CartAdapter
    private lateinit var mPresenter: CartPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setSupportActionBar(toolbar)
        setUpListeners()
        setUpPresenter()
        setUpRecycler()
        mPresenter.onUIReady(this)
    }

    private fun setUpListeners(){
        btnCheckOut.setOnClickListener{
            btnCheckOut.setOnClickListener { mPresenter.onTapCheckout() }
            ivCancel.setOnClickListener { mPresenter.onTapCancelThankYouMessage() }
        }
    }

    private fun setUpPresenter() {
        mPresenter =getPresenter<CartPresenterImpl, CartView>()
    }

    private fun setUpRecycler() {
        mBurgerAdapter = CartAdapter(mPresenter)
        rvCart.adapter = mBurgerAdapter
        rvCart.layoutManager = GridLayoutManager(applicationContext, 1)
    }

    override fun displayThankYouMessage() {
        rlThankYouMessage.visibility= View.VISIBLE
    }

    override fun hideThankYouMessage() {
        rlThankYouMessage.visibility=View.GONE
    }

    override fun displayItemsInCart(burgers: List<BurgerVO>) {
        mBurgerAdapter.setNewData(burgers)
    }
}
