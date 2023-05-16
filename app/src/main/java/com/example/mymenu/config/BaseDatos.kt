package com.example.mymenu.config

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.mymenu.global.ENUM_ESTADO_CALCULO_GC
import com.example.mymenu.global.MyData
import java.util.Date

const val TABLE_USUARIO_NAME = "CalculoGP"
private const val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_USUARIO_NAME (" + "${BaseColumns._ID} INTEGER PRIMARY KEY," + "cgcIndicadorMasaCorp REAL," + "cgcEdad INTEGER," + "cgcSexo TEXT," + "cgcResultado REAL," + "cgcEstado TEXT," + "cgcFechaCalculo TEXT)"
private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_USUARIO_NAME"

class BaseDatos(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Temporal.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},cgcIndicadorMasaCorp,cgcEdad,cgcSexo,cgcResultado,cgcEstado,cgcFechaCalculo) VALUES" + "(1,10.14,15,'M',16.12,'${ENUM_ESTADO_CALCULO_GC.OBESIDAD.name}', '${
            MyData.convertDateFormat(
                Date().time,
                "dd/MM/yyyy HH:mm:ss"
            )
        }')")
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},cgcIndicadorMasaCorp,cgcEdad,cgcSexo,cgcResultado,cgcEstado,cgcFechaCalculo) VALUES" + "(2,15.45,22,'H',10.15,'${ENUM_ESTADO_CALCULO_GC.FITNESS.name}', '${
            MyData.convertDateFormat(
                Date().time,
                "dd/MM/yyyy HH:mm:ss"
            )
        }')")
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},cgcIndicadorMasaCorp,cgcEdad,cgcSexo,cgcResultado,cgcEstado,cgcFechaCalculo) VALUES" + "(3,20.15,24,'M',19.457,'${ENUM_ESTADO_CALCULO_GC.ATLETAS.name}', '${
            MyData.convertDateFormat(
                Date().time,
                "dd/MM/yyyy HH:mm:ss"
            )
        }')")
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},cgcIndicadorMasaCorp,cgcEdad,cgcSexo,cgcResultado,cgcEstado,cgcFechaCalculo) VALUES" + "(4,24.15,28,'H',19.17,'${ENUM_ESTADO_CALCULO_GC.GRASA_ESENCIAL.name}', '${
            MyData.convertDateFormat(
                Date().time,
                "dd/MM/yyyy HH:mm:ss"
            )
        }')")
        Log.d("DB", "BASE DE DATOS CREADA CON EXITO")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate (db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}
