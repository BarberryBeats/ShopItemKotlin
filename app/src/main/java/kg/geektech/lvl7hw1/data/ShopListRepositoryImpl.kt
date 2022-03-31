package kg.geektech.lvl7hw1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.geektech.lvl7hw1.domain.ShopListRepository
import kg.geektech.lvl7hw1.domain.entities.ShopItem
import kotlin.random.Random

class ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        repeat(50) {

            val shopItem = ShopItem("Banana", 228, Random.nextBoolean())
            isShopItemIsEmpty(shopItem)
            addShopItem(shopItem)
        }
    }

    private fun isShopItemIsEmpty(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        isShopItemIsEmpty(shopItem)
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldItem = shopList[shopItem.id]
        deleteShopItem(oldItem)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList[shopItemId]}

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }

}