package com.padc.burgershop.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.burgershop.data.vos.BurgerVO

@Dao
interface BurgerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBurgers(burgers : List<BurgerVO>)

    @Query("select * from burgers")
    fun getAllBurgers():LiveData<List<BurgerVO>>

    @Query("select * from burgers where burger_id=:burgerId")
    fun findBurgerById(burgerId:Int) : LiveData<BurgerVO>

    @Query("Delete from burgers")
    fun deleteAllBurgers()


}