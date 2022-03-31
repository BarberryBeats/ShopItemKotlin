package kg.geektech.lvl7hw1.presentation.shoplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.lvl7hw1.R
import kg.geektech.lvl7hw1.domain.entities.ShopItem
import kg.geektech.lvl7hw1.presentation.diffutils.ShopListDiffCallBack
import java.lang.RuntimeException

class ShopItemsAdapter(private val onClickEnabled: (element: Boolean, shopItem: ShopItem) -> Int) : RecyclerView.Adapter<ShopItemsAdapter.ViewHolder>() {
    var shopItemList = listOf<ShopItem>()
        set(value) {
            val callback = ShopListDiffCallBack(shopItemList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> {
                R.layout.shop_item
            }
            VIEW_TYPE_DISABLED -> {
                R.layout.shop_item_disabled
            }
            else -> {
                throw RuntimeException("unknown item: $viewType")
            }
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layout, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopItemList[position]
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(shopItemList[position])
    }

    override fun getItemCount(): Int = shopItemList.size

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var tvName: TextView = itemView.findViewById(R.id.tv_name)
        private var tvCount: TextView = itemView.findViewById(R.id.tv_count)
        fun onBind(shopItem: ShopItem) {
            tvName.text = shopItem.name
            tvCount.text = shopItem.id.toString()

            itemView.setOnClickListener {
                onClickEnabled(shopItem.enabled, shopItem)
                notifyItemChanged(adapterPosition)
                //
            }

        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
    }
}
