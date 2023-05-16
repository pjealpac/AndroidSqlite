package com.example.mymenu

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GrasaCorporalActivity : AppCompatActivity() {

    private lateinit var fbtn: FloatingActionButton
    private lateinit var btn_calc_gra_corp: Button

    private lateinit var rbtn_hombre: RadioButton
    private lateinit var rbtn_mujer: RadioButton
    private lateinit var imc_text: EditText
    private lateinit var edad_text: EditText
    private var indicador_sexo = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grasa_corporal)

        btn_calc_gra_corp = findViewById(R.id.btn_calculo_grasa_corporal)
        rbtn_hombre = findViewById(R.id.opt_hombre)
        rbtn_mujer = findViewById(R.id.opt_mujer)
        imc_text = findViewById(R.id.mc_edittext)
        edad_text = findViewById(R.id.edad_edittext)

        rbtn_hombre.setOnClickListener{
            optSelected(it)
        }
        rbtn_mujer.setOnClickListener{
            optSelected(it)
        }

        btn_calc_gra_corp.setOnClickListener { view ->

            val builder: AlertDialog.Builder? = view?.let {
                AlertDialog.Builder(this)
            }
            builder?.setMessage("Los datos que proporciono son: \n" +
                    "Edad: ${edad_text.text.toString()}\n" +
                    "Indicador de Masa Corporal: ${imc_text.text.toString()}\n" +
                    "Sexo: ${if(indicador_sexo==1) "Hombre" else " Mujer"}")
                ?.setTitle("Seguro desea calcular la grasa corporal con estos datos?")

            builder?.setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(this,"Calculando...",Toast.LENGTH_LONG).show()
                })
            builder?.setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(this,"Ya puede verificar los campos",Toast.LENGTH_LONG).show()
                })
            val dialog: AlertDialog? = builder?.create()

            dialog?.show()
        }

        fbtn = findViewById(R.id.fab)
        fbtn.setOnClickListener {
            Toast.makeText(this,"Test de Floating Button",Toast.LENGTH_LONG).show()
        }
    }

    private fun optSelected(v: View){
        when(v.id){
            R.id.opt_mujer -> indicador_sexo = 0
            else -> indicador_sexo = 1
        }
    }
}