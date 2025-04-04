package org.example

import java.sql.DriverManager
import java.sql.SQLException

fun main() {
    try {
        Class.forName("org.h2.Driver")
        val conexion = DriverManager.getConnection("jdbc:h2:./BaseDatos/myDataBase","user","password")
        println("Conexión exitosa")
        conexion.close()
    } catch (e: SQLException) {
        println("No se encontró el driver jdbc: ${e.message}")
    }
}