package co.edu.eci.ieti.android.repository;

import android.app.Application;

import java.util.List;

import co.edu.eci.ieti.android.dao.TaskDAO;
import co.edu.eci.ieti.android.network.RetrofitNetwork;
import co.edu.eci.ieti.android.network.data.Task;
import co.edu.eci.ieti.android.network.service.TaskService;
import co.edu.eci.ieti.android.roomdatabase.AppRoomDatabase;

public class TaskRepository {

    private TaskDAO taskDAO;
    private TaskService service;

    public TaskRepository(String token,Application application){
        taskDAO = AppRoomDatabase.getDatabase(application).taskDAO();
        service = new RetrofitNetwork(token).getTaskService();
    }

    public List<Task> getTasks(){
        try{
            List<Task> tasks = service.getTasks().execute().body();
            if(tasks!=null){
                taskDAO.deleteAll();
                for(Task task:tasks) addTask(task);
            }
        }catch(Exception e){ }
        return taskDAO.getTasks();
    }

    public void addTask(Task task){
        service.addTask(task);
        taskDAO.insert(task);
    }

}
