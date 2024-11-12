package com.example.tugasretrofit

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class UserDetailActivity : AppCompatActivity() {

    private lateinit var userName: TextView
    private lateinit var userEmail: TextView
    private lateinit var userAvatar: ImageView
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        // Inisialisasi views
        userName = findViewById(R.id.tv_user_name)
        userEmail = findViewById(R.id.tv_user_email)
        userAvatar = findViewById(R.id.img_user_avatar)
        backButton = findViewById(R.id.backButton)

        // Mendapatkan data yang dikirimkan dari MainActivity
        val name = intent.getStringExtra("userName") ?: "Unknown"
        val email = intent.getStringExtra("userEmail") ?: "Unknown"
        val avatarUrl = intent.getStringExtra("userAvatar") // URL gambar

        // Menampilkan data pada TextViews
        userName.text = name
        userEmail.text = email

        // Memuat gambar menggunakan Glide
        Glide.with(this)
            .load(avatarUrl)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error)
            .into(userAvatar)

        // Menangani klik tombol back
        backButton.setOnClickListener {
            onBackPressed() // Kembali ke Activity sebelumnya
        }
    }
}