package com.stop.loveam.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stop.loveam.R;
import com.stop.loveam.entity.News;
import com.stop.loveam.view.NewsAdapterA;
import com.stop.loveam.view.NewsAdapterB;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class RecommendFragment extends Fragment {

    private RecyclerView recyclerView;

    private RecyclerView newsView;

    private NewsAdapterA adapterA;

    private NewsAdapterB adapterB;
    private List<News> newsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.newsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        newsView = view.findViewById(R.id.newsView);
        newsView.setLayoutManager(new LinearLayoutManager(getContext()));

        newsList = getNewsList(); // Implement this method to get your news data
        adapterA = new NewsAdapterA(newsList);
        adapterB = new NewsAdapterB(newsList);
        recyclerView.setAdapter(adapterA);
        newsView.setAdapter(adapterB);
    }

    private List<News> getNewsList() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News("水果", "新闻1的内容", "https://img3.redocn.com/tupian/20140827/shuiguosucai_2970523.jpg", "2024-5-11","水果日报"));
        return newsList;
    }
}