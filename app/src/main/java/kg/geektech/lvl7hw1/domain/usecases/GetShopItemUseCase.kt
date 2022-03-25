package kg.geektech.lvl7hw1.domain.usecases

import kg.geektech.lvl7hw1.domain.ShopListRepository
import kg.geektech.lvl7hw1.domain.entities.ShopItem

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }

}