package Ejercicio3

import java.sql.DriverManager

fun consultarLineasPedido() {
    try {
        val conn = DriverManager.getConnection("jdbc:h2:./BaseDatos/myDataBase", "user", "password")
        val stmt = conn.prepareStatement("SELECT * FROM LINEAPEDIDO WHERE idpedido = ?")
        stmt.setInt(1, 1)

        val rs = stmt.executeQuery()
        println("Líneas de pedido para el pedido con ID 1:")
        while (rs.next()) {
            println("ID: ${rs.getInt("id")}, Cantidad: ${rs.getInt("cantidad")}, Precio: ${rs.getInt("precio")}, ID Pedido: ${rs.getInt("idpedido")}, ID Producto: ${rs.getInt("idproducto")}")
        }

        rs.close()
        stmt.close()
        conn.close()
    } catch (e: Exception) {
        println("Error al consultar las líneas de pedido: ${e.message}")
    }
}

fun consultarTotalPedidosAtaulfo() {
    try {
        val conn = DriverManager.getConnection("jdbc:h2:./BaseDatos/myDataBase", "user", "password")
        val stmt = conn.prepareStatement("""
            SELECT SUM(preciototal) AS total 
            FROM PEDIDO 
            WHERE idusuario = (SELECT id FROM USUARIO WHERE nombre = ?)
        """.trimIndent())
        stmt.setString(1, "Ataúlfo Rodríguez")

        val rs = stmt.executeQuery()
        if (rs.next()) {
            println("Total de pedidos de Ataúlfo Rodríguez: ${rs.getDouble("total")}")
        }

        rs.close()
        stmt.close()
        conn.close()
    } catch (e: Exception) {
        println("Error al consultar el total de pedidos de Ataúlfo Rodríguez: ${e.message}")
    }
}

fun consultarUsuariosQueCompraronAbanico() {
    try {
        val conn = DriverManager.getConnection("jdbc:h2:./BaseDatos/myDataBase", "user", "password")
        val stmt = conn.prepareStatement("""
            SELECT DISTINCT U.nombre
            FROM USUARIO U
            JOIN PEDIDO P ON U.id = P.idusuario
            JOIN LINEAPEDIDO LP ON P.id = LP.idpedido
            JOIN PRODUCTO PR ON LP.idproducto = PR.id
            WHERE PR.nombre = ?
        """.trimIndent())
        stmt.setString(1, "Abanico")

        val rs = stmt.executeQuery()
        println("Usuarios que compraron un Abanico:")
        while (rs.next()) {
            println(rs.getString("nombre"))
        }

        rs.close()
        stmt.close()
        conn.close()
    } catch (e: Exception) {
        println("Error al consultar los usuarios que compraron un abanico: ${e.message}")
    }
}

fun main() {
    consultarLineasPedido()
    println("----------------------------")
    consultarTotalPedidosAtaulfo()
    println("----------------------------")
    consultarUsuariosQueCompraronAbanico()
}
