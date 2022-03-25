package kg.geektech.lvl7hw1.domain.usecases

import kg.geektech.lvl7hw1.domain.ShopListRepository
import kg.geektech.lvl7hw1.domain.entities.ShopItem

class GetShopListUseCase(private val shopListRepository: ShopListRepository){
    fun getShopList(): List<ShopItem>{
        return shopListRepository.getShopList()
    }
}