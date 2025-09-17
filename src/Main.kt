import kotlinx.coroutines.*

fun main() = runBlocking{

    //punto de entrada del programa

    println("Sistema FoodExpress")
    val catalogo = inicializarCatalogo()

    println("Catálogo disponible: ")

    catalogo.forEachIndexed { index, producto -> val precioFinal = producto.calcularPrecio()
    println("${index+1}. ${producto.nombre} - $${"%,d".format(precioFinal)}" +
    if (producto is Comida && producto.premium) "(Premium)" else "")}

    println("Seleccione productos (números separados por coma): ")
    val seleccion = readLine()?.split(",")?.mapNotNull{
        it.trim().toIntOrNull()?.let { i -> if (i in 1.. catalogo.size) catalogo[i-1]
        else null}
    }?: emptyList()

    if (seleccion.isEmpty()){
        println("No selecciono productos válidos.")
        return@runBlocking
    }

    println("Cliente tipo (regular/vip/premium): ")
    val tipoString = when (readLine()?.trim()?.lowercase()){
        "regular" -> TipoCliente.regular
        "vip" -> TipoCliente.vip
        "premium" -> TipoCliente.premium
        else -> {
            println("Tipo de cliente invalido, se usara REGULAR")
            TipoCliente.regular
        }
    }

    println("Procesando pedido...")
    val estadoFinal = procesarPedido(seleccion)
    mostrarResumen(seleccion, tipoString, estadoFinal)
}