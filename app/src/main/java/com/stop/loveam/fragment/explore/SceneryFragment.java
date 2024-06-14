package com.stop.loveam.fragment.explore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.stop.loveam.R;
import com.stop.loveam.dao.ExploreDao;
import com.stop.loveam.dao.Impl.ExploreDaoImpl;
import com.stop.loveam.utils.DownloadPhotoUtil;
import com.stop.loveam.utils.GlideEngine;
import com.stop.loveam.utils.ImageUtil;

import java.io.File;
import java.util.List;

public class SceneryFragment extends Fragment {

    private ImageView mImageScenery1;

    private ImageView mImageScenery2;

    private ImageView mImageScenery3;

    List<String> url;

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
                .into(mImageScenery1);
        new Thread(()->{
            ExploreDao exploreDao = new ExploreDaoImpl();
            url = exploreDao.getRandomPictures(requireContext());

            try {
                requireActivity().runOnUiThread(()->{
                    GlideEngine.createGlideEngine().loadImage(getContext(), url.get(0), mImageScenery1);
                    GlideEngine.createGlideEngine().loadImage(getContext(), url.get(1), mImageScenery2);
                    GlideEngine.createGlideEngine().loadImage(getContext(), url.get(2), mImageScenery3);
                });
            } catch (Exception e) {
                Log.e("SceneryFragment", e.toString());
                requireActivity().finish();
            }

        }).start();

        mImageScenery1.setOnLongClickListener(v -> {
            // 处理长按事件
            try {
                DownloadPhotoUtil.requestPermission(requireActivity(), ImageUtil.createBitmapFromView(mImageScenery1));
                //Toast.makeText(requireContext(), "图片已保存至相册", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d("CalendarFragment", "e: " + e);
                //Toast.makeText(requireContext(), "图片保存失败", Toast.LENGTH_SHORT).show();
            }
            return true;
        });

        mImageScenery2.setOnLongClickListener(v -> {
            // 处理长按事件
            try {
                DownloadPhotoUtil.requestPermission(requireActivity(), ImageUtil.createBitmapFromView(mImageScenery2));
                //Toast.makeText(requireContext(), "图片已保存至相册", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d("CalendarFragment", "e: " + e);
                //Toast.makeText(requireContext(), "图片保存失败", Toast.LENGTH_SHORT).show();
            }
            return true;
        });

        mImageScenery3.setOnLongClickListener(v -> {
            // 处理长按事件
            try {
                DownloadPhotoUtil.requestPermission(requireActivity(), ImageUtil.createBitmapFromView(mImageScenery3));
                //Toast.makeText(requireContext(), "图片已保存至相册", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d("CalendarFragment", "e: " + e);
                //Toast.makeText(requireContext(), "图片保存失败", Toast.LENGTH_SHORT).show();
            }
            return true;
        });


        return view;

    }

    @Override
    public void onStop() {
        super.onStop();
        //删除url中的本地文件
        if (url != null) {
            for (String s : url) {
                File file = new File(s);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    public void initView(View view) {
        mImageScenery1 = view.findViewById(R.id.imageScenery1);
        mImageScenery2 = view.findViewById(R.id.imageScenery2);
        mImageScenery3 = view.findViewById(R.id.imageScenery3);
    }
}