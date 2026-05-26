package marquez.kimberly.tareasapp
enum class TaskOrder(val displayName: String, val queryValue: String) {
    RECIENTES_PRIMERO("Más recientes primero", "RECIENTES"),
    ANTIGUAS_PRIMERO("Más antiguas primero", "ANTIGUAS"),
    TITULO_AZ("Título A-Z", "TITULO_AZ"),
    TITULO_ZA("Título Z-A", "TITULO_ZA")
}