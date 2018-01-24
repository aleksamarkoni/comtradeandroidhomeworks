package com.example.code.comtradetodo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return new TodoAdapter.TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.titleTextView.setText(todo.getTitle());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        public TodoViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.todo_title);
        }
    }
}
