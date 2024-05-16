package com.stop.loveam.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.rex.editor.view.RichEditorNew;
import com.stop.loveam.R;

public class EditActivity extends AppCompatActivity{

    private RichEditorNew richEditor;
    private GridView gvList;
    private View tools;
    private Context mContext;
    public final static String TAG = "rex";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);

        richEditor.setOnTextChangeListener(new RichEditorNew.OnTextChangeNewListener() {
            @Override
            public void onTextChange(String text) {
                Log.i(TAG, "text:" + text);
            }
        });

        richEditor.setOnConsoleMessageListener(new RichEditorNew.OnConsoleMessageListener() {
            @Override
            public void onTextChange(String message, int lineNumber, String sourceID) {
                if (message != null && message.contains("|")) {
                    Toast.makeText(EditActivity.this, "message:" + message, Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "message:" + message);
            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    public void onBackPressed(View view) {
        Log.d("EditActivity", String.valueOf(view.getId()));
        switch (view.getId()){
            case R.id.bold_btn:
                richEditor.setBold();
                break;
            case R.id.title_btn:
                finish();
                break;
            case R.id.list_btn:
                finish();
                break;
            case R.id.image_btn:
                richEditor.insertImage("TEST_IMAGE_URL");
                break;
            case  R.id.video_btn:
                richEditor.insertVideo("TEST_VIDEO_URL");
                break;
        }
    }
}