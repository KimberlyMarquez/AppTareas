package marquez.kimberly.tareasapp
import  androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query(
        "SELECT * FROM tasks"
    )

    fun getAllTasks(): Flow<List<TaskEntity>>

    @Insert
    suspend fun insert(task: TaskEntity): Long

    @Update
    suspend fun update(task: TaskEntity)

    @Delete
    suspend fun  delete(task: TaskEntity)

    @Query("""
        SELECT * FROM tasks
        WHERE titulo LIKE '%' || :query || '%'
        ORDER BY  
            CASE WHEN :orderBy = 'RECIENTES' THEN creado_en END DESC,
            CASE WHEN :orderBy = 'ANTIGUAS' THEN creado_en END ASC,
            CASE WHEN :orderBy = 'TITULO_AZ' THEN LOWER(titulo) END ASC,
            CASE WHEN :orderBy = 'TITULO_ZA' THEN LOWER(titulo) END DESC
            
    """)

    fun searchTasks(query: String, orderBy: String): Flow<List<TaskEntity>>
}