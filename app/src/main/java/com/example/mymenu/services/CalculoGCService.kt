package com.example.mymenu.services

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.mymenu.config.BaseDatos
import com.example.mymenu.config.TABLE_USUARIO_NAME
import com.example.mymenu.core.CalculoGC
import com.example.mymenu.global.MyData
import java.text.SimpleDateFormat

val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

class CalculoGCService(context: Context) {
    val dbHelper = BaseDatos(context)

    fun insertarNewCalculoGC(parCalculo: CalculoGC): CalculoGC? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(
                "cgcIndicadorMasaCorp",
                parCalculo.cgcIndicadorMasaCorp
            )
            put("cgcEdad", parCalculo.cgcEdad)
            put("cgcSexo", parCalculo.cgcSexo.toString())
            put("cgcResultado", parCalculo.cgcResultado)
            put("cgcEstado", parCalculo.cgcEstado)
            put(
                "cgcFechaCalculo",
                MyData.convertDateFormat(parCalculo.cgcFechaCalculo.time, "dd/MM/yyyy HH:mm:ss")
            )
        }
        val newRowId = db?.insert(
            TABLE_USUARIO_NAME,
            null,
            values
        )
        cerrarDB()
        return if (newRowId != null) getCalculo(newRowId.toLong()) else null
    }

    fun updateCalculo(parCalculo: CalculoGC): CalculoGC? {
        val db =
            dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(
                "cgcIndicadorMasaCorp",
                parCalculo.cgcIndicadorMasaCorp
            )
            put("cgcEdad", parCalculo.cgcEdad)
            put("cgcSexo", parCalculo.cgcSexo.toString())
            put("cgcResultado", parCalculo.cgcResultado)
            put("cgcEstado", parCalculo.cgcEstado)
            put(
                "cgcFechaCalculo",
                MyData.convertDateFormat(parCalculo.cgcFechaCalculo.time, "dd/MM/yyyy HH:mm:ss")
            )
        }
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("${parCalculo.cgcId}")
        val count = db.update(TABLE_USUARIO_NAME, values, selection, selectionArgs)
        cerrarDB()
        return if (count == 1) parCalculo else null
    }

    fun getCalculo(idCalculoGc: Long): CalculoGC? {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            "cgcIndicadorMasaCorp",
            "cgcEdad",
            "cgcSexo",
            "cgcResultado",
            "cgcEstado",
            "cgcFechaCalculo"
        )
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("$idCalculoGc")
        val cursor = db.query(
            TABLE_USUARIO_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor) {
            if (cursor.count == 1) {
                while (moveToNext()) {
                    val indicadorMC = getDouble(getColumnIndexOrThrow("cgcIndicadorMasaCorp"))
                    val edad = getInt(getColumnIndexOrThrow("cgcEdad"))
                    val sexo = getString(getColumnIndexOrThrow("cgcSexo"))
                    val resultado = getDouble(getColumnIndexOrThrow("cgcResultado"))
                    val estado = getString(getColumnIndexOrThrow("cgcEstado"))
                    val fechaCalculo = getString(getColumnIndexOrThrow("cgcFechaCalculo"))
                    val calculoId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                    val calculoObJ = CalculoGC(
                        calculoId,
                        indicadorMC,
                        edad,
                        sexo[0],
                        resultado,
                        estado,
                        formatter.parse(fechaCalculo)
                    )
                    cerrarDB()
                    return calculoObJ
                }
            } else {
                return null
            }
        }
        return null
    }

    fun getCalculos(): MutableMap<Int, CalculoGC> {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            "cgcIndicadorMasaCorp",
            "cgcEdad",
            "cgcSexo",
            "cgcResultado",
            "cgcEstado",
            "cgcFechaCalculo"
        )
        val cursor = db.query(
            TABLE_USUARIO_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        val calculosObjs = mutableMapOf<Int, CalculoGC>()
        with(cursor) {
            while (moveToNext()) {
                val index = getInt(getColumnIndexOrThrow(BaseColumns._ID))
                val indicadorMC = getDouble(getColumnIndexOrThrow("cgcIndicadorMasaCorp"))
                val edad = getInt(getColumnIndexOrThrow("cgcEdad"))
                val sexo = getString(getColumnIndexOrThrow("cgcSexo"))
                val resultado = getDouble(getColumnIndexOrThrow("cgcResultado"))
                val estado = getString(getColumnIndexOrThrow("cgcEstado"))
                val fechaCalculo = getString(getColumnIndexOrThrow("cgcFechaCalculo"))
                val calculoId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val calculoObJ = CalculoGC(
                    calculoId,
                    indicadorMC,
                    edad,
                    sexo[0],
                    resultado,
                    estado,
                    formatter.parse(fechaCalculo)
                )
                calculosObjs.put(index, calculoObJ)
            }
        }
        cerrarDB()
        return calculosObjs
    }

    fun deleteCalculo(idCalculoGC: Long): Boolean {
        val db = dbHelper.writableDatabase
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("${idCalculoGC}")
        val deletedRows =
            db.delete(TABLE_USUARIO_NAME, selection, selectionArgs)
        cerrarDB()
        return deletedRows > 0
    }

    fun cerrarDB(){
        dbHelper.close()
    }
}