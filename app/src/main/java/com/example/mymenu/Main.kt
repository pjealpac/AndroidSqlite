package com.example.mymenu

import kotlin.random.Random
import kotlin.reflect.typeOf

fun main() {
    /**
     * TODO
     * 1. Ver Variables
     * 2. Ver If-else
     * 3. Funciones
     * 4. Arrays
     * 5. Listas
     * 6. POO
     */

    //EJEMPLO DE COMENTARIO

/*
    val seconds = 3
    println("Iniciando sistema en " + seconds + " segundos")
    Thread.sleep(3000)
    val edad: Int = 15
    val peso: Double = 79.4512
    val nombre: String = "Jesus Alpaca Rendon"
    val sexo: Char = 'M'
    val nacPeru: Boolean = true

    println("Hola soy $nombre de $edad, ${ if (nacPeru) "nacido en Peru" else "no naci en Peru"}, sexo: $sexo y con un peso de $peso")


    var nuevaEdad: Int = 11
    while (nuevaEdad < 18){
        nuevaEdad+=1
        if(nuevaEdad >= 18){
            println("Ya eres mayor de edad")
        } else {
            println("En crecimiento... edad: $nuevaEdad")
        }

    }

    nuevaEdad = Random.nextInt(7, 30)
    println("Nueva Edad: $nuevaEdad")
    when(nuevaEdad) {
        18 -> println("Joven, Mayor de Edad")
        17,16,15,14,13,12 -> println("Adolescente")
        11,10,9,8,7 -> println("Niño")
        25 -> println("Adulto")
        else -> println("No indicado en el sistema")
    }
*/
    /*
    var a = true; var b = true
    a && b // && == AND
    a || b // || == OR
    < > >= <= == !=
    */

    /*
    fun esVocal(c: Char) : String {
        var result = ""
        result = when (c) {
            'a', 'e', 'i', 'o', 'u' -> "$c es una vocal"
            else -> "$c no es una vocal"
        }
        return result
    }

    println(esVocal('c'))
    println(esVocal('i'))

    val arrayMeses: Array<String> = arrayOf("Enero", "Febrero","Marzo", "Abril", "Mayo","Junio", "Julio","")

    arrayMeses.set(7,"Agosto")

    for (mes in arrayMeses)
        println("Mes $mes")

    for (indice in arrayMeses.indices)
        println("Mes ${arrayMeses.get(indice)}")

    for (i in 0 .. arrayMeses.size - 1)
        println("Mes ${i+1} es ${arrayMeses.get(i)}")
    */

    /*var dnis: Set<Int> = setOf(15772424,74671245,70451263, 84541011)

    if (dnis.contains(70451263)) println("DNI encontrado")
    if (dnis.isEmpty()) println("Set vacio") else println("No esta vacio, cantidad: ${dnis.size}")*/

   /* var dnis: MutableSet<Int> = mutableSetOf(15772424,74671245,70451263, 84541011)

    if (dnis.contains(70451263)) println("DNI encontrado")
    if (dnis.isEmpty()) println("Set vacio") else println("No esta vacio, cantidad: ${dnis.size}")

    dnis.add(74512243)

    println("Anadiendo un nuevo item...")
    dnis.forEach { println(it) }

    dnis.remove(74512243)
    println("Removiendo un item...")
    dnis.forEach { println(it) }

    dnis.clear()
    println("Vacio...")
    dnis.forEach { println(it) }*/

    /*
    fun loteria(nroSuerte: Int) : Int? {
        var randomNumber = Random.nextInt(1, 6)
        println("El numero fue: $randomNumber")
        return if (randomNumber == nroSuerte) nroSuerte else null
    }

    print("Ingrese el numero de la loteria: ")
    val nroLoteria = readln().toInt()
    var resultado: Int? = loteria(nroLoteria)
    println(resultado)
    println(resultado is Int)
    println(resultado?.compareTo(2))
    println(if(resultado!=null) "Si es un entero" else "No es un entero")
    println(resultado!! > 5)
*/


    //MAPS
    var registroPersonas: MutableMap<Int,String> = mutableMapOf(
        71415245 to "Juan Mendoza",
        15421548 to "Fabiola Martinez",
        71244200 to "Julio Quispe",
        64212402 to "Maria Guzman"
    )

    registroPersonas.put(75421242,"Carol Jimenez")
    println(registroPersonas)
    println(registroPersonas.values.last())

    registroPersonas.forEach { entry ->
        println("${entry.key} : ${entry.value}")
    }

    for (value in registroPersonas.values) {
        println(value)
    }

    var compare = { x: String, y: String ->
        if(x.length < y.length) y else x
    }

    println(" El usuario con mas digitos es: ${compare(registroPersonas.getValue(15421548),registroPersonas.getValue(71415245))}")

    println(registroPersonas.filter( {x -> x.value.length > 12}))

}