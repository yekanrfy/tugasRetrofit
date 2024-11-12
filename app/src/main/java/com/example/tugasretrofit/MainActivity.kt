package com.example.tugasretrofit

import UserAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tugasretrofit.model.User
import com.example.tugasretrofit.network.ApiClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView // Mengganti rvUsers dengan listView
    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Pastikan layout ini memiliki fragment_container

        // Inisialisasi BottomNavigationView
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Inisialisasi ListView
        listView = findViewById(R.id.listView)
        fetchData()

        // Menambahkan OnItemClickListener pada ListView
        listView.setOnItemClickListener { parent, view, position, id ->
            val user = users[position]  // Ambil data user yang dipilih

            // Membuat Intent untuk membuka UserDetailActivity
            val intent = Intent(this, UserDetailActivity::class.java).apply {
                putExtra("userName", "${user.firstName} ${user.lastName}")
                putExtra("userEmail", user.email)
                putExtra("userAvatar", user.avatar) // URL gambar avatar
            }

            // Memulai UserDetailActivity
            startActivity(intent)
        }
    }

    private fun fetchData() {
        // Menggunakan instance ApiClient yang sudah dibuat di ApiClient singleton
        val apiService = ApiClient.getInstance() // Memanggil getInstance() langsung

        // Menjalankan coroutine untuk fetch data API
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getAllUsers()
            if (response.isSuccessful) {
                // Menambahkan data ke list users jika request berhasil
                response.body()?.data?.let { fetchedUsers ->
                    users.addAll(fetchedUsers)

                    // Berpindah ke MainThread untuk update UI
                    withContext(Dispatchers.Main) {
                        // Menetapkan adapter ListView dengan data yang sudah didapat
                        val adapter = UserAdapter(this@MainActivity, users)
                        listView.adapter = adapter
                    }
                }
            } else {
                // Menangani kasus error atau response tidak berhasil
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}