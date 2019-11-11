package com.padc.burgershop.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "burgers")
 class BurgerVO {
    @ColumnInfo(name = "burger_id")
    @PrimaryKey(autoGenerate = true)
    var burgerId: Int =0

    @ColumnInfo(name = "burger_name")
    var burgerName: String = ""

    @ColumnInfo(name = "burger_description")
    var burgerDescription: String=""

    @ColumnInfo(name = "burger_image_url")
    var burgerImageUrl: String = ""
}