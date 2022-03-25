package kg.geektech.lvl7hw1.presentation

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

    private val _shopListLD = MutableLiveData<List<ShopItem>>()
    private val _shopItemLD = MutableLiveData<ShopItem>()

    val shopListLD: LiveData<List<ShopItem>>
        get() = _shopListLD
    val shopItemLD: LiveData<ShopItem>
        get() = _shopItemLD

    fun getShopList() {
        _shopListLD.value = getShopListUseCase.getShopList()
    }

    fun getShopItem(shopItemId: Int){
        _shopItemLD.value = getShopItemUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(shopItem: ShopItem){
        addShopItemUseCase.addShopItem(shopItem)
        getShopList()
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun editShopItem(shopItem: ShopItem){
        shopItem.enabled = !shopItem.enabled
        editShopItemUseCase.editShopItem(shopItem)
        _shopItemLD.value = getShopItemUseCase.getShopItem(shopItem.id)
    }

}