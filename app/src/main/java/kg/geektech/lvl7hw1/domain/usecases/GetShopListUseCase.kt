package kg.geektech.lvl7hw1.domain.usecases

import androidx.lifecycle.LiveData
import kg.geektech.lvl7hw1.domain.ShopListRepository
import kg.geektech.lvl7hw1.domain.entities.ShopItem

class GetShopListUseCase(private val shopListRepository: ShopListRepository){
    fun getShopList(): LiveData<List<ShopItem>>{
        return shopListRepository.getShopList()
    }
}