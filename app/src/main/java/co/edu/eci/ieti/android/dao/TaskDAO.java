package co.edu.eci.ieti.android.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import co.edu.eci.ieti.android.network.data.Task;

@Dao
public interface TaskDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);
    @Query("SELECT * FROM task_table")
    List<Task> getTasks();
    @Query("DELETE FROM task_table")
    void deleteAll();
}
