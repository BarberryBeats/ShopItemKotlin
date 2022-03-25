package kg.geektech.lvl7hw1.data

import kg.geektech.lvl7hw1.domain.ShopListRepository
import kg.geektech.lvl7hw1.domain.entities.ShopItem

class ShopListRepositoryImpl: ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    private fun isShopItemIsEmpty(shopItem: ShopItem){
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        isShopItemIsEmpty(shopItem)
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        //
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopList[shopItem.id] = shopItem
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList[shopItemId]
    }

    override fun getShopList(): List<ShopItem> {
        return shopList
    }
}