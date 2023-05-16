package com.example.mymenu.global

import android.app.Application
import android.text.format.DateFormat
import com.example.mymenu.core.CalculoGC
import java.util.*

enum class ENUM_ESTADO_CALCULO_GC {
    GRASA_ESENCIAL,
    ATLETAS,
    FITNESS,
    ACEPTABLE,
    OBESIDAD
}

class MyData : Application() {

    companion object {
//        var listaCalculosGC: MutableMap<Int,CalculoGC> = mutableMapOf(
//            1 to CalculoGC(15.45,15,'M',16.12,ENUM_ESTADO_CALCULO_GC.ACEPTABLE.name, Date()),
//            2 to CalculoGC(10.14,22,'H',10.15,ENUM_ESTADO_CALCULO_GC.FITNESS.name, Date()),
//            3 to CalculoGC(20.15,23,'M',19.457,ENUM_ESTADO_CALCULO_GC.ATLETAS.name, Date()),
//        )

        fun convertDateFormat(time: Long, format: String): String{
            val calendar = Calendar.getInstance(Locale.getDefault())
            calendar.timeInMillis = (time +1000L)
            val dateString = DateFormat.format(format,calendar).toString().replaceFirstChar{ it.uppercaseChar()}
            return dateString
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}