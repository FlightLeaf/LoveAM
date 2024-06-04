package com.stop.loveam.fragment.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.stop.loveam.R;
import com.stop.loveam.dao.ExploreDao;
import com.stop.loveam.dao.Impl.ExploreDaoImpl;
import com.stop.loveam.utils.GlideEngine;

public class SceneryFragment extends Fragment {

    private ImageView mImageScenery;
    private Button mRefreshBtn;
    private Button mDownloadBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenery, container, false);
        initView(view);
        Glide.with(requireActivity())
                .asGif()
                .load(R.drawable.book2)
                .into(mImageScenery);
        new Thread(()->{
            ExploreDao exploreDao = new ExploreDaoImpl();
            String url = exploreDao.getRandomPicture();

            requireActivity().runOnUiThread(()->{
                GlideEngine.createGlideEngine().loadImage(getContext(), url, mImageScenery);
            });
        }).start();

        return view;
    }

    public void initView(View view) {
        mImageScenery = view.findViewById(R.id.imageScenery);
        mRefreshBtn = view.findViewById(R.id.refreshBtn);
        mDownloadBtn = view.findViewById(R.id.downloadBtn);
    }
}