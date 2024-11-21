package id.ellinda.newsapp.BeritaAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ellinda.newsapp.R
import id.ellinda.newsapp.BeritaAdapter.news 
import id.ellinda.newsapp.databinding.LayBeritaBinding 

// konstruktor kelas NewsAdapter menerima 2 parameter (context, dan list)
// NewsAdapter mewarisi dari RecyclerView.Adapter
// MyViewHolder sebagai kelas ViewHolder
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
    // Metode onBindViewHolder -> mengikat data berita ke ViewHolder pada posisi tertentu d
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataNews = list[position]
        holder.setData(dataNews)
    }
    // Metode setOnClickCallback -> untuk menetapkan callback ketika item berita di klik
    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallBack = onItemClickCallback
    }
    // Interface -> untuk menangani klik pada item berita
    interface OnItemClickCallback {
        fun onItemClick(data: news)
    }

    // getItemCount -> mengembalikan jumlah item dalam daftar berita
    override fun getItemCount(): Int {
        return list.size
    }
}
