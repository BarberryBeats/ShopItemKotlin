package kg.geektech.lvl7hw1.domain.usecases

import kg.geektech.lvl7hw1.domain.ShopListRepository
import kg.geektech.lvl7hw1.domain.entities.ShopItem

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }

}