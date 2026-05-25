package marquez.kimberly.tareasapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.Volatile

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract  class AppDatabase: RoomDatabase(){
    abstract fun taskDao(): TaskDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Tareas que se cargan la primera vez que se
        // instala la app. Edita esta lista con tus
        // tareas reales del reto con el socio formador.
        private val TAREAS_INICIALES = listOf(
            TaskEntity(
                titulo = "Create comments section layout",
                completado = true
            ),
            TaskEntity(
                titulo = "Create comments bottom sheet UI",
                completado = true
            ),
            TaskEntity(
                titulo = "Implement comment card UI",
                completado = true
            ),
            TaskEntity(
                titulo = "Implement nested thread/reply UI",
                completado = true
            ),
            TaskEntity(
                titulo = "Implement timestamp display",
                completado = true
            ),
            TaskEntity(
                titulo = "Create comment input section",
                completado = true
            ),
            TaskEntity(
                titulo = "Create empty thread state UI",
                completado = true
            ),
            TaskEntity(
                titulo = "Implement Navigation Bar",
                completado = true
            ),

            TaskEntity(
                titulo = "Implement avatar and author display",
                completado = false
            ),
            TaskEntity(
                titulo = "Implement keyboard-safe layout spacing",
                completado = false
            ),
            TaskEntity(
                titulo = "Apply responsive styling",
                completado = false
            ),
            TaskEntity(
                titulo = "Apply app design system styles",
                completado = false
            ),
            TaskEntity(
                titulo = "Adjust image top/bottom paddings",
                completado = false
            ),
            TaskEntity(
                titulo = "Add \"Me\" indicator UI",
                completado = true
            ),
            TaskEntity(
                titulo = "Add \"Replying to...\" label UI",
                completado = true
            ),
            TaskEntity(
                titulo = "Admin delete comments (Functionality)",
                completado = true
            ),
            TaskEntity(
                titulo = "Allow users to delete their comments",
                completado = true
            ),
            TaskEntity(
                titulo = "Allow users to post reply",
                completado = true
            ),
            TaskEntity(
                titulo = "Load comments with threads & authors",
                completado = true
            ),
            TaskEntity(
                titulo = "Allow replies to comments",
                completado = true
            ),
            TaskEntity(
                titulo = "Create thread (Functionality)",
                completado = true
            )
        )

        fun getInstance(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(
                this
            ){
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "tasks_db"
                    )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Insertamos las tareas iniciales en un
                            // hilo separado. NUNCA en el main thread.
                            CoroutineScope(Dispatchers.IO).launch {
                                val dao = getInstance(context).taskDao()
                                TAREAS_INICIALES.forEach { tarea ->
                                    dao.insert(tarea)
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}