open class Producto
    (val nombre: String,
            val precio: Int,
            val categoria: String,
            val tiempoPreparación: Int){

    //clase base y derivadas

    open fun calcularPrecio(): Int = precio
    //se necesita anteponer un open para que se pueda activar el override
}

class Comida
    (nombre: String,
            precio: Int,
            val premium: Boolean): Producto(nombre, precio, "Comida", 5){
    override fun calcularPrecio(): Int{
        return if (premium) (precio*1.2).toInt()
                else precio
    }
            }
enum class Tamaño{
    pequeño, mediano, grande
}

class Bebida
    (nombre: String,
            precio: Int,
            val tamaño: Tamaño): Producto(nombre, precio, "Bebida", 2){
    override fun calcularPrecio(): Int {
        return when (tamaño){
            Tamaño.pequeño -> precio
            Tamaño.mediano -> (precio*1.15).toInt()
            Tamaño.grande ->  (precio*1.3).toInt()
        }
    }
            }
