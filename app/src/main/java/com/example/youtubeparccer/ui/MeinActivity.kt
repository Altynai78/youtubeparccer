package com.example.youtubeparccer.ui

import android.content.ClipData
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeparccer.base.BaseActivity
import com.example.youtubeparccer.ui.adapters.PlayListAdapter

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var adapter: PlayListAdapter

    override fun setUI() {
        super.setUI()
        adapter = PlayListAdapter(this::onClick)
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): MainViewModel {
        return ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.getPlaylist().observe(this) {
            binding.rvPlaylist.adapter = adapter
            adapter.addList(it.items)
        }
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) {
            if (it){
                binding.clNoInternet.isVisible = false
                binding.llMainLayout.isVisible = true
            } else {
                binding.clNoInternet.isVisible = true
                binding.llMainLayout.isVisible = false
            }
        }
    }

    private fun onClick(item: ClipData.Item) {
        val intent = Intent(this, PlaylistActivity::class.java)
        intent.putExtra(KEY_FOR_ID, item.id)
        startActivity(intent)
    }

    companion object {
        const val KEY_FOR_ID = "FADFA"
    }
}
