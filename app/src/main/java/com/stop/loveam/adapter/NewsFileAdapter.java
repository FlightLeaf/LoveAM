package com.stop.loveam.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stop.loveam.R;

import java.util.List;

public class NewsFileAdapter extends RecyclerView.Adapter<NewsFileAdapter.NewsFileViewHolder>{


    private final List<String> fileList;

    public NewsFileAdapter(List<String> fileList) {
        this.fileList = fileList;
    }

    @NonNull
    @Override
    public NewsFileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.note_file_item, null);
        return new NewsFileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFileViewHolder holder, int position) {
        String file_name = fileList.get(position);
        holder.file_name_temp.setText(file_name);
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public static class NewsFileViewHolder extends RecyclerView.ViewHolder {
        TextView file_name_temp; // 新闻标题
        public NewsFileViewHolder(View itemView) {
            super(itemView);
            file_name_temp = itemView.findViewById(R.id.file_name_temp);
        }
    }
}
