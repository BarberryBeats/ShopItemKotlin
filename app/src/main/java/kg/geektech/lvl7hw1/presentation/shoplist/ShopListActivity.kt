package kg.geektech.lvl7hw1.presentation.shoplist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.lvl7hw1.databinding.ActivityShopListBinding
import kg.geektech.lvl7hw1.domain.entities.ShopItem
import kg.geektech.lvl7hw1.presentation.shoplist.ShopItemsAdapter.Companion.VIEW_TYPE_DISABLED
import kg.geektech.lvl7hw1.presentation.shoplist.ShopItemsAdapter.Companion.VIEW_TYPE_ENABLED

class ShopListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopListBinding
    private lateinit var adapter: ShopItemsAdapter
    private lateinit var viewModel: ShopListViewModel
    private var shopList = listOf<ShopItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ShopListViewModel::class.java]
        initView()
        initObservers()
    }

    private fun initObservers() {
        viewModel.shopListLD.observe(this) {
            Log.e("Ray", "ao$it")
            adapter.shopItemList = it
            shopList = it
        }
    }

    private fun initView() {
        adapter = ShopItemsAdapter(this::onClickEnabled)
        binding.shopListRecycler.adapter = adapter
        setupSwipeListener(binding.shopListRecycler)
    }

    private fun setupSwipeListener(rv: RecyclerView) {
        val callBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                rv.adapter?.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.shopItemList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rv)
    }

    private fun onClickEnabled(enabled: Boolean, shopItem: ShopItem): Int {
        return if (enabled) {
            shopItem.enabled = false
            VIEW_TYPE_DISABLED
        } else {
            shopItem.enabled = true
            VIEW_TYPE_ENABLED
        }
    }
}