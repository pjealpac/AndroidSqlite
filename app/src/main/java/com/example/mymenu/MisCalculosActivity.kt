package com.example.mymenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mymenu.adapters.CalculoAdapter
import com.example.mymenu.core.CalculoGC
import com.example.mymenu.global.MyData
import com.example.mymenu.services.CalculoGCService
import com.google.android.material.appbar.MaterialToolbar

class MisCalculosActivity : AppCompatActivity() {

    var calculoService = CalculoGCService(this)

    lateinit var listaCalculosLocal: MutableMap<Int,CalculoGC>
    lateinit var recycler: RecyclerView
    lateinit var pullToRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_calculos)
        val toolbar: MaterialToolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        pullToRefresh = findViewById(R.id.swipeRefreshLayout)

        pullToRefresh.setOnRefreshListener {
            pullToRefresh.isRefreshing = false
            updateLista()
        }

        listaCalculosLocal = calculoService.getCalculos()

        recycler = findViewById(R.id.mis_calculos_recycler_view)

        val adapter = CalculoAdapter(listaCalculosLocal)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

        recycler.adapter = adapter
    }

    fun updateLista(){
        listaCalculosLocal = calculoService.getCalculos()
        val nuevoAdapter = CalculoAdapter(listaCalculosLocal)
        recycler.adapter = nuevoAdapter
    }
}