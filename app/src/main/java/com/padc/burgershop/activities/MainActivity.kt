package com.padc.burgershop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.padc.burgershop.R
import com.padc.burgershop.adapters.BurgerAdapter
import com.padc.burgershop.data.vos.BurgerVO
import com.padc.burgershop.mvp.presenters.MainPresenter
import com.padc.burgershop.mvp.presenters.impl.MainPresenterImpl
import com.padc.burgershop.mvp.views.MainView
import kotlinx.android.synthetic.main.activity_main.*

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

    override fun navigateToDetailScreen(burgerId: Int) {
        startActivity(BurgerDetailActivity.newIntent(this,burgerId))
    }

    override fun navigateToCartScreen() {
        startActivity(CartActivity.newIntent(this))
    }

    override fun addBurgerToCart(burger: BurgerVO) {
//        mPresenter.onTapAddToCart(burger)
    }

    override fun dispalyCountInCart(burgerCount: Int) {
        tvCartCount.text = burgerCount.toString()
    }


}

