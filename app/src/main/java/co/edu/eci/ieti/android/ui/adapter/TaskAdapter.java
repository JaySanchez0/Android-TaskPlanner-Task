package co.edu.eci.ieti.android.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.eci.ieti.R;
import co.edu.eci.ieti.android.network.data.Task;

public class TaskAdapter
        extends RecyclerView.Adapter<TaskAdapter.ViewHolder>
{

    List<Task> taskList = null;

    public TaskAdapter(List<Task> tasks) {
       this.taskList = tasks;
       notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType )
    {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.task_row, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder, int position )
    {
        Task task = taskList.get( position );
        System.out.println("-------------------- Entro");
        ((TextView)holder.itemView.findViewById(R.id.title)).setText(task.getDescription());
        ((TextView)holder.itemView.findViewById(R.id.date)).setText("Date: "+task.getDueDate().toString());
        ((TextView)holder.itemView.findViewById(R.id.desc)).setText("Priority: "+task.getPriority());
    }

    @Override
    public int getItemCount()
    {
        System.out.println("----------------------------- Size ----------------------"+taskList.size());
        return taskList.size();
    }

    public void updateTasks(List<Task> tasks){
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    class ViewHolder
            extends RecyclerView.ViewHolder
    {
        ViewHolder( @NonNull View itemView )
        {
            super( itemView);
        }
    }

}
