package kg.geektech.lvl7hw1.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geektech.lvl7hw1.data.ShopListRepositoryImpl
import kg.geektech.lvl7hw1.domain.entities.ShopItem
import kg.geektech.lvl7hw1.domain.usecases.*

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl()
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val _shopItemLD = MutableLiveData<ShopItem>()
    val shopItemLD: LiveData<ShopItem>
        get() = _shopItemLD


    val shopListLD = getShopListUseCase.getShopList()

    fun getShopItem(shopItemId: Int) {
        _shopItemLD.value = getShopItemUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(shopItem: ShopItem) {
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

}