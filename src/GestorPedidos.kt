import kotlinx.coroutines.*
import kotlin.math.roundToInt

val IVA = 0.19

enum class TipoCliente(val descuento: Double){

    //funciones de negocio

    regular(0.05), vip(0.10), premium(0.15)

}

fun inicializarCatalogo(): List<Producto> = listOf(
    Comida("Pollo a lo Pobre", 7990, premium = false),
    Comida("Lomo al Vino", 15990, premium = true),
    Bebida("Sprite", 1990, Tamaño.mediano),
    Bebida("Fanta", 2990, Tamaño.grande)
)

fun aplicarDescuento(subtotal: Int, tipoCliente: TipoCliente): Int{
    return (subtotal*tipoCliente.descuento).toInt()
}

fun calcularIva(monto: Int): Int{
    return (monto*IVA).toInt()
}

fun mostrarResumen
            (productos: List<Producto>,
             tipoClinte: TipoCliente,
             estadoFinal: EstadoPedido){
                val precios = productos.map { it.calcularPrecio() }
                val subtotal = precios.sum()
                val descuento = aplicarDescuento(subtotal, tipoClinte)
                val iva = calcularIva(subtotal-descuento)
                val total = subtotal-descuento+iva

    println("Resumen del Pedido")

    productos.forEachIndexed { index, prod ->

        println("- ${prod.nombre}: $${"%,d".format(precios[index])}")

    }

    println("Subtotal: $${"%,d".format(subtotal)}")

    println("Descuento ${tipoClinte.name} (${(tipoClinte.descuento * 100).roundToInt()}%): -$${"%,d".format(descuento)}")

    println("IVA (19%): $${"%,d".format(iva)}")

    println("TOTAL: $${"%,d".format(total)}")

    println("Estado final: ${estadoFinal::class.simpleName}")

            }

suspend fun procesarPedido(productos: List<Producto>): EstadoPedido{
    return try {
        println("Estado: En Preparación")
        delay(3000)
        EstadoPedido.ListoParaEntrega
    }catch (e: Exception){
        EstadoPedido.Error("Fallo en la preparación")
    }
}