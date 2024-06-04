package com.stop.loveam.fragment.explore;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.stop.loveam.R;
import com.stop.loveam.dao.ExploreDao;
import com.stop.loveam.dao.Impl.ExploreDaoImpl;
import com.stop.loveam.entity.RESTCalendar;
import com.stop.loveam.utils.DownloadPhotoUtil;
import com.stop.loveam.utils.GlideEngine;
import com.stop.loveam.utils.ImageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CalendarFragment extends Fragment {

    private ImageView mImageCalendar;
    private Button mButtonSave;
    private TextView mTextTitle;
    String mUrl;
    private TextView mTextRand;

    private String savedPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator  + String.valueOf(System.currentTimeMillis()) + ".png";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        initView(view);
        Glide.with(requireActivity())
                .asGif()
                .load(R.drawable.book2)
                .into(mImageCalendar);

        new Thread(() -> {

            try {
                ExploreDao exploreDao = new ExploreDaoImpl();
                RESTCalendar restCalendar = exploreDao.getRESTCalendar();
                String rand = exploreDao.getRandomSaying();
                if (restCalendar == null || rand == null){
                    Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Thread.sleep(500);
                    requireActivity().runOnUiThread(() -> {
                        mTextTitle.setText(restCalendar.getTitle());
                        mTextRand.setText(rand);
                        GlideEngine.createGlideEngine().loadImage(getContext(), restCalendar.getUrl(), mImageCalendar);
                    });
                    mUrl = restCalendar.getUrl();
                }

            } catch (Exception e) {
                Log.d("CalendarFragment", "e: " + e);
            }



        }).start();

        mButtonSave.setOnClickListener(v -> {

            try {
//                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED
//                        || ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//                } else {
//                    //保存图片到文件夹
//                    Log.d("CalendarFragment", "mUrl: " + mUrl);
//                    Bitmap bitmap = createViewBitmap(mImageCalendar);
//                    //获取时间戳
//                    String time = String.valueOf(System.currentTimeMillis());
//                    saveMyBitmap(time, bitmap);
//                    Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
//                }
                DownloadPhotoUtil.requestPermission(requireActivity(), ImageUtil.createBitmapFromView(mImageCalendar));
            } catch (Exception e) {
                Log.d("CalendarFragment", "e: " + e);
            }
        });

        return view;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    private void initView(View view) {
        mImageCalendar = view.findViewById(R.id.imageCalendar);
        mButtonSave = view.findViewById(R.id.buttonSave);
        mTextTitle = view.findViewById(R.id.textTitle);
        mTextRand = view.findViewById(R.id.textRand);
    }

    //使用IO流将bitmap对象存到本地指定文件夹
    public void saveMyBitmap(final String bitName, final Bitmap bitmap) {
        new Thread(() -> {
            String filePath = Environment.getExternalStorageDirectory().getPath();
            System.out.println("filePath: " + savedPath);
            File file = new File(savedPath);
            try {
                file.createNewFile();

                FileOutputStream fOut = null;
                fOut = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                Log.d("CalendarFragment", "e: " + e);
            }
        }).start();
    }
    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }
}