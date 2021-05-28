package com.example.ca_kotlin

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ca_kotlin.adapters.VehicleAdapter
import com.example.ca_kotlin.api.ApiClient
import com.example.ca_kotlin.api.Vehicles
import com.example.ca_kotlin.dao.AppDatabaseHelper
import com.example.ca_kotlin.dao.Vehicle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var data: ArrayList<Vehicles> = ArrayList()

    companion object {
        const val PROGRESS_BAR_TITLE = "Récupération de la liste des véhicules..."
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.list_vehicules)
        recycler.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle(MainActivity.PROGRESS_BAR_TITLE)
        progressDialog.setCancelable(false)
        progressDialog.show()

        val adapter = VehicleAdapter(data)
        recycler.adapter = adapter

        global(adapter, recycler, progressDialog)
    }

    private fun global(adapter: VehicleAdapter, recycler: RecyclerView, progressDialog: ProgressDialog) {
        ApiClient.getClient.getVehicles().enqueue(object : Callback<List<Vehicles>> {
            override fun onResponse(call: Call<List<Vehicles>>?, response: Response<List<Vehicles>>?) {
                data.clear()
                data.addAll(response!!.body()!!)
                adapter.update(data.clone() as ArrayList<Vehicles>)
                adapter.notifyDataSetChanged()
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<List<Vehicles>>?, t: Throwable?) {
                progressDialog.dismiss()
            }
        })
    }

    override fun onBackPressed() {
        this.finishAffinity()
    }

}
