import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productapp.data.model.ProductModel
import com.example.productapp.databinding.ProductLineBinding

class ListProductAdapter( private var productList: List<ProductModel>) : RecyclerView.Adapter<ListProductViewHolder>() {

    private var prodList: List<ProductModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductViewHolder {
        val binding = ProductLineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun updateProdList(list: List<ProductModel>) {
        prodList = list
        notifyDataSetChanged()
    }
}
