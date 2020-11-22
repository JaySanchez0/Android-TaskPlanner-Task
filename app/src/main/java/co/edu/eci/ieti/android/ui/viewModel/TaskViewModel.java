package co.edu.eci.ieti.android.ui.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.edu.eci.ieti.android.network.data.Task;
import co.edu.eci.ieti.android.repository.TaskRepository;

public class TaskViewModel extends AndroidViewModel {
    private MutableLiveData<List<Task>> tasks;
    private TaskRepository taskRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    public TaskViewModel(Application application){
        super(application);
    }

    public LiveData<List<Task>> getTasks(){
        if(tasks==null){
            tasks = new MutableLiveData<List<Task>>();
            loadTasks();
        }
        return tasks;
    }

    public void setToken(String token){
        taskRepository = new TaskRepository(token,getApplication());;
    }

    private void loadTasks(){
        executorService.execute(()->tasks.postValue(taskRepository.getTasks()));
    }

}
