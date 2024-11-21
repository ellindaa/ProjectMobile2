package id.ellinda.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.View
import id.ellinda.newsapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    // variabel binding dideklarasikan dengan 'lateinit' yaitu akan diinisialisasi nanti

    companion object {
        const val cont_TitleNews = "cont_TitleNews"
        const val cont_PhotoNews = "cont_PhotoNews"
        const val cont_KontenNews = "cont_KontenNews"
    }
    // companion object -> untuk mendeklarasikan konstanta yang dapat diakses tanpa membuat instance dari kelas
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root) 

        supportActionBar?.hide()
        // menyembunyikan action bar

        // mengambil data dari intent
        // intent adalah cara untuk berkomunikasi dan berinteraksi antar bagian dalam aplikasi android
        binding.txtTitleNews.text = intent.getStringExtra(cont_TitleNews) ?: "No Title"
        binding.txtKontenNews.text = intent.getStringExtra(cont_KontenNews) ?: "No Content"
        // menampilkan gambar 
        intent.getStringExtra(cont_PhotoNews)?.toIntOrNull()?.let {
            binding.imgToolbar.setImageResource(it)
        }
        // tombol kembali
        binding.btnBack.setOnClickListener {
            val toMain = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(toMain)
            finish()
        }
    }
}
