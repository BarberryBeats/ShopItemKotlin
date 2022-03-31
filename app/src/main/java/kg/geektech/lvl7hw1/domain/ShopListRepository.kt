package kg.geektech.lvl7hw1.domain

import androidx.lifecycle.LiveData
import kg.geektech.lvl7hw1.domain.entities.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

}