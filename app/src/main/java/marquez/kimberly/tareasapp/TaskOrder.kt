package marquez.kimberly.tareasapp

enum class TaskOrder(val displayName: String) {
    RECIENTES_PRIMERO("Más recientes primero"),
    ANTIGUAS_PRIMERO("Más antiguas primero"),
    TITULO_AZ("Título A-Z"),
    TITULO_ZA("Título Z-A")
}