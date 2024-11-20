package id.ellinda.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.View
import id.ellinda.newsapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val cont_TitleNews = "cont_TitleNews"
        const val cont_PhotoNews = "cont_PhotoNews"
        const val cont_KontenNews = "cont_KontenNews"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.txtTitleNews.text = intent.getStringExtra(cont_TitleNews) ?: "No Title"
        binding.txtKontenNews.text = intent.getStringExtra(cont_KontenNews) ?: "No Content"

        intent.getStringExtra(cont_PhotoNews)?.toIntOrNull()?.let {
            binding.imgToolbar.setImageResource(it)
        }

        binding.btnBack.setOnClickListener {
            val toMain = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(toMain)
            finish()
        }
    }
}