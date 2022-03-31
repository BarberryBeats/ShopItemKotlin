package kg.geektech.lvl7hw1.presentation.shoplist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geektech.lvl7hw1.data.ShopListRepositoryImpl
import kg.geektech.lvl7hw1.domain.entities.ShopItem
import kg.geektech.lvl7hw1.domain.usecases.DeleteShopItemUseCase
import kg.geektech.lvl7hw1.domain.usecases.EditShopItemUseCase
import kg.geektech.lvl7hw1.domain.usecases.GetShopItemUseCase
import kg.geektech.lvl7hw1.domain.usecases.GetShopListUseCase

class ShopListViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl()
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val _shopItemLD = MutableLiveData<ShopItem>()

    val shopListLD = getShopListUseCase.getShopList()
    val shopItemLD: LiveData<ShopItem>
        get() = _shopItemLD

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun getShopItem(shopItemId: Int): ShopItem{
        return getShopItemUseCase.getShopItem(shopItemId)
    }

}