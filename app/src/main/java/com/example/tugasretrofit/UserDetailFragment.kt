package com.example.tugasretrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class UserDetailFragment : Fragment() {

    private lateinit var userName: TextView
    private lateinit var userEmail: TextView
    private lateinit var userAvatar: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi TextView dan ImageView
        userName = view.findViewById(R.id.tv_user_name)
        userEmail = view.findViewById(R.id.tv_user_email)
        userAvatar = view.findViewById(R.id.img_user_avatar) // Pastikan ID ini sesuai dengan layout Anda

        // Menerima data dari arguments
        val name = arguments?.getString("userName") ?: "Unknown"
        val email = arguments?.getString("userEmail") ?: "Unknown"
        val avatarUrl = arguments?.getString("userAvatar") // Menerima URL gambar

        // Menampilkan data yang diterima ke TextView
        userName.text = name
        userEmail.text = email

        // Memuat gambar dengan Glide
        Glide.with(this)
            .load(avatarUrl) // Menggunakan URL gambar yang diterima
            .placeholder(R.drawable.ic_placeholder) // Placeholder saat loading
            .error(R.drawable.ic_error) // Gambar error jika loading gagal
            .into(userAvatar)

        // Menambahkan tombol back untuk kembali
        val backButton = view.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            // Kembali ke fragment sebelumnya
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}