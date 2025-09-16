sealed class EstadoPedido{

    //sealed class para estados

    object Pendiente: EstadoPedido()
    object EnPreparación: EstadoPedido()
    object ListoParaEntrega: EstadoPedido()

    data class Error(val mensaje: String): EstadoPedido()

}