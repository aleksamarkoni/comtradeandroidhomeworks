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

        return new TodoAdapter.TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TodoViewHolder holder, int position) {
        final Todo todo = todoList.get(position);
        holder.titleTextView.setText(todo.getTitle());
        holder.isDoneCheckBox.setChecked(todo.isDone());
        holder.isDoneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                todo.setDone(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private CheckBox isDoneCheckBox;
        public TodoViewHolder(View itemView) {
            super(itemView);
            isDoneCheckBox = itemView.findViewById(R.id.todo_done_checkbox);
            titleTextView = itemView.findViewById(R.id.todo_title);
        }
    }
}
