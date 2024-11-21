package id.ellinda.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.ellinda.newsapp.BeritaAdapter.BeritaModel
import id.ellinda.newsapp.BeritaAdapter.NewsAdapter
import id.ellinda.newsapp.BeritaAdapter.news
import id.ellinda.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // menampilkan berita headline
        if (BeritaModel.newslist.isNotEmpty()) {
            val headline = BeritaModel.newslist.last()
            binding.tvwTitleHeadLine.text = headline.title
            binding.tvwDescHeadline.text = headline.desc
            binding.imgNews0.setImageResource(headline.photo)

            binding.cdvNewsheadline.setOnClickListener {
                val detailIntent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.cont_TitleNews, headline.title)
                    putExtra(DetailActivity.cont_KontenNews, headline.desc)
                    putExtra(DetailActivity.cont_PhotoNews, headline.photo.toString())
                }
                startActivity(detailIntent)
            }
        }

        // mengatur recycler view
        // recycler view adalah komponen UI untuk menampilkan daftar data dalam bentuk daftar atau grid    
        binding.rcvDaftarberita.layoutManager = LinearLayoutManager(this)

        newsAdapter = NewsAdapter(this, BeritaModel.newslist)
        binding.rcvDaftarberita.adapter = newsAdapter

        newsAdapter.setOnClickCallback(object : NewsAdapter.OnItemClickCallback {
            override fun onItemClick(data: news) {
                val detailIntent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.cont_TitleNews, data.title)
                    putExtra(DetailActivity.cont_KontenNews, data.desc)
                    putExtra(DetailActivity.cont_PhotoNews, data.photo.toString())
                }
                startActivity(detailIntent)
            }
        })
    }
}
