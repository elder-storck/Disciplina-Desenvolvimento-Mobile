import androidx.recyclerview.widget.RecyclerView
import com.example.productapp.data.model.ProductModel
import com.example.productapp.databinding.ProductLineBinding

class ListProductViewHolder(
    private val binding: ProductLineBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductModel) {
        binding.textProdNameAnswer.text = product.name
        binding.textProdCodAnswer.text  = product.prodCode
    }
}
