package com.example.code.comtradetodo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> todoList;

    public TodoAdapter(List<Todo> todoList) {
        this.todoList = todoList;
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
        //TODO ovde podesiti vreme na text view, prvo proveriti da li uopste treba da se podesi vreme
        //TODO ako su hour i min i dalje -1, to znaci da ovaj todo nema notification time. 2 poena
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private CheckBox isDoneCheckBox;
        //TODO dodati link za TextView koji prikazuje vremene 0 poena

        public TodoViewHolder(View itemView) {
            super(itemView);
            isDoneCheckBox = itemView.findViewById(R.id.todo_done_checkbox);
            titleTextView = itemView.findViewById(R.id.todo_title);
        }
    }
}
