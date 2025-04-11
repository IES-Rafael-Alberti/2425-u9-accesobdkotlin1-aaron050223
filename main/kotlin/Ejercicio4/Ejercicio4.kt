package Ejercicio4

import java.sql.DriverManager

fun eliminarUsuarioCornelio() {
    try {
        val conn = DriverManager.getConnection("jdbc:h2:./BaseDatos/myDataBase", "user", "password")
        val stmt = conn.prepareStatement("DELETE FROM USUARIO WHERE nombre = ?")
        stmt.setString(1, "Cornelio Ramírez")

        val filasAfectadas = stmt.executeUpdate()
        println("Usuarios eliminados: $filasAfectadas")

        stmt.close()
        conn.close()
    } catch (e: Exception) {
        println("Error al eliminar al usuario Cornelio Ramírez: ${e.message}")
    }
}

fun eliminarProductoPorPrecio() {
    try {
        val conn = DriverManager.getConnection("jdbc:h2:./BaseDatos/myDataBase", "user", "password")
        val stmt = conn.prepareStatement("DELETE FROM PRODUCTO WHERE precio = ?")
        stmt.setDouble(1, 24.99)

        val filasAfectadas = stmt.executeUpdate()
        println("Productos eliminados con precio 24,99 €: $filasAfectadas")

        stmt.close()
        conn.close()
    } catch (e: Exception) {
        println("Error al eliminar el producto con precio 24,99 €: ${e.message}")
    }
}

fun eliminarPedidoConLineas() {
    try {
        val conn = DriverManager.getConnection("jdbc:h2:./BaseDatos/myDataBase", "user", "password")

        // Primero elimino las líneas de pedido asociadas
        val stmtLineas = conn.prepareStatement("DELETE FROM LINEAPEDIDO WHERE idpedido = ?")
        stmtLineas.setInt(1, 3)
        val lineasAfectadas = stmtLineas.executeUpdate()
        println("Líneas de pedido eliminadas para el pedido con ID 3: $lineasAfectadas")
        stmtLineas.close()

        // Ahora elimino el pedido
        val stmtPedido = conn.prepareStatement("DELETE FROM PEDIDO WHERE id = ?")
        stmtPedido.setInt(1, 3)
        val pedidosAfectados = stmtPedido.executeUpdate()
        println("Pedidos eliminados con ID 3: $pedidosAfectados")
        stmtPedido.close()

        conn.close()
    } catch (e: Exception) {
        println("Error al eliminar el pedido con ID 3: ${e.message}")
    }
}

fun main() {
    eliminarUsuarioCornelio()
    println("----------------------------")
    eliminarProductoPorPrecio()
    println("----------------------------")
    eliminarPedidoConLineas()
}

