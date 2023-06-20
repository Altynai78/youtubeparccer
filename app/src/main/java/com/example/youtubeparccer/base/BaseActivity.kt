package com.example.youtubeparccer.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel


abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    protected abstract fun inflateViewBinding(): VB
    protected abstract fun setViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        viewModel = setViewModel()
        setContentView(binding.root)

        checkInternet()
        setUI()
        setupLiveData()
        initClickListener()
    }

    open fun setupLiveData() {
        // Инициализация LiveData и наблюдателей
    }

    open fun setUI() {
        // Инициализация UI компонентов
    }

    open fun initClickListener() {
        // Обработка кликов
    }

    open fun checkInternet() {
        // Проверка наличия интернета
    }
}
