package id.ellinda.newsapp.BeritaAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ellinda.newsapp.R
import id.ellinda.newsapp.BeritaAdapter.news // Pastikan ini sesuai dengan struktur Anda
import id.ellinda.newsapp.databinding.LayBeritaBinding // Pastikan ini sesuai dengan nama file layout Anda

class NewsAdapter(private val context: Context, private val list: List<news>) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private lateinit var onItemClickCallBack: OnItemClickCallback

    inner class MyViewHolder(private val binding: LayBeritaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(currentNews: news) {
            binding.tvwTitle.text = currentNews.title
            binding.tvwDesc.text = currentNews.desc
            binding.imgNews.setImageResource(currentNews.photo)

            itemView.setOnClickListener {
                onItemClickCallBack.onItemClick(currentNews)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = LayBeritaBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataNews = list[position]
        holder.setData(dataNews)
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallBack = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClick(data: news)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}