package com.example.code.comtradetodo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.code.comtradetodo.Utils.DecodePictureAsyncTask;
import com.example.code.comtradetodo.Utils.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> implements ItemTouchHelperAdapter {

    private List<Todo> todoList;
    private OnTodoDoneListener onTodoDoneListener;

    public TodoAdapter(List<Todo> todoList, OnTodoDoneListener onTodoDoneListener) {
        this.todoList = todoList;
        this.onTodoDoneListener = onTodoDoneListener;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(todoList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(todoList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        Todo todo = todoList.remove(position);
        onTodoDoneListener.onItemRemoved(todo.getDatabaseId());
        notifyItemRemoved(position);
    }

    public interface OnTodoDoneListener {
        void onDoneClicked(Todo todo);
        void onItemRemoved(int databaseId);
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo_list_item, parent, false);
        CheckBox checkBox = view.findViewById(R.id.todo_done_checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int position = (Integer) buttonView.getTag();
                Todo todo = todoList.get(position);
                todo.setDone(isChecked);
                if (onTodoDoneListener != null) {
                    onTodoDoneListener.onDoneClicked(todo);
                }
            }
        });

        return new TodoAdapter.TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TodoViewHolder holder, int position) {
        final Todo todo = todoList.get(position);
        holder.titleTextView.setText(todo.getTitle());
        holder.isDoneCheckBox.setTag(position);
        holder.isDoneCheckBox.setChecked(todo.isDone());
        if (todo.getPictureFileUri() != null) {
            new DecodePictureAsyncTask(holder.imageView, holder.progressBar).execute(todo.getPictureFileUri());
        } else {
            holder.imageView.setVisibility(View.INVISIBLE);
        }
        //TODO ovde podesiti vreme na text view, prvo proveriti da li uopste treba da se podesi vreme
        //TODO ako su hour i min i dalje -1, to znaci da ovaj todo nema notification time. 2 poena
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ProgressBar progressBar;
        private TextView titleTextView;
        private CheckBox isDoneCheckBox;
        //TODO dodati link za TextView koji prikazuje vremene 0 poena

        public TodoViewHolder(View itemView) {
            super(itemView);
            isDoneCheckBox = itemView.findViewById(R.id.todo_done_checkbox);
            titleTextView = itemView.findViewById(R.id.todo_title);
            imageView = itemView.findViewById(R.id.todoPicture);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
