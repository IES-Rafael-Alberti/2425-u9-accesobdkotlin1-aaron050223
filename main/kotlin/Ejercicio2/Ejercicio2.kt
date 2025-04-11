package Ejercicio2

import java.sql.DriverManager

data class Usuario(val id: Int, val nombre: String, val email: String)
data class Producto(val id: Int, val nombre: String, val precio: Double, val stock: Int)
data class Pedido(val id: Int, val preciototal: Double, val idusuario: Int)
data class LineaPedido(val id: Int, val cantidad: Int, val precio: Int, val idpedido: Int, val idproducto: Int)

fun insertarUsuarios() {
    // Me conecto a la base de datos
    val conn = DriverManager.getConnection("jdbc:h2:./BaseDatos/myDataBase", "user", "password")
    val clearStmt = conn.createStatement() // Esto lo pongo porque tuve un problema y añadí lo que no era, asi, siempre que se ejecuta se borra lo anterior
    clearStmt.executeUpdate("DELETE FROM USUARIO")
    clearStmt.close()
    val stmt = conn.prepareStatement("INSERT INTO Usuario(id, nombre, email) VALUES (?, ?, ?)")

    // Lista de usuarios que voy a meter
    val usuarios = listOf(
        Usuario(1, "Facundo Pérez", "facuper@mail.com"),
        Usuario(2, "Ataúlfo Rodríguez", "ataurod@mail.com"),
        Usuario(3, "Cornelio Ramírez", "Cornram@mail.com")
    )

    for (usuario in usuarios) {
        stmt.setInt(1, usuario.id) // Uso setInt para el id
        stmt.setString(2, usuario.nombre) // Uso setString para el nombre
        stmt.setString(3, usuario.email) // Uso setString para el correo
        stmt.executeUpdate() // Ejecutar la inserción
        println("Usuario ${usuario.nombre} insertado correctamente.")
    }

    // Cerrar la conexión y el statement
    stmt.close()
    conn.close()
}

fun insertarProductos() {
    // Me conecto a la base de datos
    val conn = DriverManager.getConnection("jdbc:h2:./BaseDatos/myDataBase", "user", "password")
    val clearStmt = conn.createStatement() // Esto lo pongo porque tuve un problema y añadí lo que no era, asi, siempre que se ejecuta se borra lo anterior
    clearStmt.executeUpdate("DELETE FROM PRODUCTO")
    clearStmt.close()
    val stmt = conn.prepareStatement("INSERT INTO PRODUCTO(id, nombre, precio, stock) VALUES (?, ?, ?, ?)")

    // Lista de usuarios que voy a meter
    val productos = listOf(
        Producto(1, "Ventilador", 10.00,2),
        Producto(2, "Abanico", 150.00, 47),
        Producto(3, "Estufa", 24.99, 1)
    )

    for (producto in productos) {
        stmt.setInt(1, producto.id)
        stmt.setString(2, producto.nombre)
        stmt.setDouble(3, producto.precio) // Uso setDouble para el precio
        stmt.setInt(4, producto.stock)
        stmt.executeUpdate() // Ejecutar la inserción
        println("Producto ${producto.nombre} insertado correctamente.")
    }

    // Cerrar la conexión y el statement
    stmt.close()
    conn.close()
}

fun insertarPedido() {
    // Me conecto a la base de datos
    val conn = DriverManager.getConnection("jdbc:h2:./BaseDatos/myDataBase", "user", "password")
    val clearStmt = conn.createStatement() // Esto lo pongo porque tuve un problema y añadí lo que no era, asi, siempre que se ejecuta se borra lo anterior
    clearStmt.executeUpdate("DELETE FROM PEDIDO")
    clearStmt.close()
    val stmt = conn.prepareStatement("INSERT INTO PEDIDO(id, preciototal, idusuario) VALUES (?, ?, ?)")

    // Lista de usuarios que voy a meter
    val pedidos = listOf(
        Pedido(1, 160.00, 2),
        Pedido(2, 20.00, 1),
        Pedido(3, 150.00, 2)
    )

    for (pedido in pedidos) {
        stmt.setInt(1, pedido.id)
        stmt.setDouble(2, pedido.preciototal)
        stmt.setInt(3, pedido.idusuario)
        stmt.executeUpdate() // Ejecutar la inserción
        println("Pedido ${pedido.id} insertado correctamente.")
    }

    // Cerrar la conexión y el statement
    stmt.close()
    conn.close()
}

fun insertarLineaPedido() {
    // Me conecto a la base de datos
    val conn = DriverManager.getConnection("jdbc:h2:./BaseDatos/myDataBase", "user", "password")
    val clearStmt = conn.createStatement() // Esto lo pongo porque tuve un problema y añadí lo que no era, asi, siempre que se ejecuta se borra lo anterior
    clearStmt.executeUpdate("DELETE FROM LINEAPEDIDO")
    clearStmt.close()
    val stmt = conn.prepareStatement("INSERT INTO LINEAPEDIDO(id, cantidad, precio, idpedido, idproducto) VALUES (?, ?, ?, ?, ?)")

    // Lista de usuarios que voy a meter
    val pedidosLina = listOf(
        LineaPedido(1, 1, 10,1,1),
        LineaPedido(2, 1, 150,1,2),
        LineaPedido(3, 2, 20,2,1),
        LineaPedido(4, 1, 150,3,2),
    )

    for (pedidoLinea in pedidosLina) {
        stmt.setInt(1, pedidoLinea.id)
        stmt.setInt(2, pedidoLinea.cantidad)
        stmt.setInt(3, pedidoLinea.precio)
        stmt.setInt(4, pedidoLinea.idpedido)
        stmt.setInt(5, pedidoLinea.idproducto)
        stmt.executeUpdate() // Ejecutar la inserción
        println("Pedido ${pedidoLinea.id} insertado correctamente.")
    }

    // Cerrar la conexión y el statement
    stmt.close()
    conn.close()
}

fun main() {
    insertarUsuarios()
    println("----------------------------")
    insertarProductos()
    println("----------------------------")
    insertarPedido()
    println("----------------------------")
    insertarLineaPedido()
}
