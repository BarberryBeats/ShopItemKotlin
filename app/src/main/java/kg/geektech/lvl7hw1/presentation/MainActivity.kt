package kg.geektech.lvl7hw1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kg.geektech.lvl7hw1.databinding.ActivityMainBinding
import kg.geektech.lvl7hw1.domain.entities.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val shopItemList = mutableListOf<ShopItem>()
    private var shopItemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.shopListLD.observe(this) {
            Log.e("Ray", "ShopList: $it")
        }
        viewModel.shopItemLD.observe(this){
            Log.e("Ray", "ShopItem: " )
        }
    }


    private fun initListeners() {
        binding.apply {
            btnAdd.setOnClickListener {
                viewModel.addShopItem(
                    ShopItem("cucumbers", 2, false)
                )
                shopItemList.add(ShopItem("cucumbers", 2, false, shopItemId++))
            }
            btnDelete.setOnClickListener {
                viewModel.deleteShopItem(shopItemList[etTypeId.print()])
            }

            btnGetItem.setOnClickListener {
                viewModel.getShopItem(etTypeId.print())
            }

            btnGetList.setOnClickListener {
                viewModel.getShopList()
            }

            btnEdit.setOnClickListener {
                viewModel.editShopItem(shopItemList[etTypeId.print()])
            }

        }

    }
}