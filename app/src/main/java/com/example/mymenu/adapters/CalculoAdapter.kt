package com.example.mymenu.adapters

import android.app.AlertDialog
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mymenu.R
import com.example.mymenu.core.CalculoGC
import com.example.mymenu.services.CalculoGCService
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*


class CalculoAdapter(private val data: MutableMap<Int, CalculoGC>?) :
    RecyclerView.Adapter<CalculoAdapter.CalculoHolder>() {

    inner class CalculoHolder(val v: View) : RecyclerView.ViewHolder(v) {

        var tituloItemLista: TextView
        var estadoItemLista: TextView
        var fechaItemLista: TextView
        var dataItemLista: TextView
        var updatebtnLista: MaterialButton

        init {
            tituloItemLista = v.findViewById(R.id.titulo_item_lista)
            estadoItemLista = v.findViewById(R.id.estado_item_lista)
            fechaItemLista = v.findViewById(R.id.fecha_item_lista)
            dataItemLista = v.findViewById(R.id.data_item_lista)
            updatebtnLista = v.findViewById(R.id.updateCalculo_btn_lista)
        }

        fun bindData(data: CalculoGC) = with(v) {
            tituloItemLista.text = "Resultado: ${data.cgcResultado}%"
            estadoItemLista.text = data.cgcEstado.uppercase(Locale.getDefault())
            fechaItemLista.text =
                "Fecha: ${convertDateFormat(data.cgcFechaCalculo.time, "dd/MM/yyyy HH:mm:ss")}"
            dataItemLista.text =
                "Datos \nEdad: ${data.cgcEdad} años \nMasa Corporal: ${data.cgcIndicadorMasaCorp}"

            updatebtnLista.setOnClickListener {
                val builder = AlertDialog.Builder(v.context, R.style.CustomAlertDialog).create()
                val viewCustom = LayoutInflater.from(v.context).inflate(R.layout.layout_actualizar_registro, null)
                val imctxtupdate = viewCustom.findViewById<TextInputLayout>(R.id.alertdialog_imc_textField)
                val edadtxtupdate = viewCustom.findViewById<TextInputLayout>(R.id.alertdialog_edad_textField)
                val buttonupdate = viewCustom.findViewById<Button>(R.id.dialogDismiss_button)
                (imctxtupdate.editText as? TextInputEditText)?.setText(data.cgcIndicadorMasaCorp.toString())
                (edadtxtupdate.editText as? TextInputEditText)?.setText(data.cgcEdad.toString())
                builder.setView (viewCustom)
                buttonupdate.setOnClickListener {
                    val calculoService = CalculoGCService(v.context)
                    data.cgcEdad = (edadtxtupdate.editText as? TextInputEditText)?.text.toString().toInt()
                    data.cgcIndicadorMasaCorp = (imctxtupdate.editText as? TextInputEditText)?.text.toString().toDouble()
                    calculoService.updateCalculo (data)
                    Toast.makeText (viewCustom.context, "Actualizado con exito", Toast.LENGTH_LONG).show()
                    builder.dismiss()
                    }
                builder.setCanceledOnTouchOutside (true)
                builder.show()
            }
        }

        fun convertDateFormat(time: Long, format: String): String {
            val calendar = Calendar.getInstance(Locale.getDefault())
            calendar.timeInMillis = (time + 1000L)
            val dateString = DateFormat.format(format, calendar).toString()
                .replaceFirstChar { it.uppercaseChar() }
            return dateString
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculoHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lista_calculos, parent, false)
        return CalculoHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: CalculoHolder, position: Int) {
        data?.let {
            it.get(position + 1)?.let { it1 -> holder.bindData(it1) }
        }
    }
}