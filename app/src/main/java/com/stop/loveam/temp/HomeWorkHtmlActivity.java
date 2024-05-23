package com.stop.loveam.temp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.stop.loveam.R;

public class HomeWorkHtmlActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et;

    private Handler handler;

    private Handler handler1;

    ImageView iv;

    TextView tv;

    public void getwebinfo() {
        try {
            URL uRL = new URL(this.et.getText().toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String str = bufferedReader.readLine();
                if (str != null) {
                    stringBuffer.append(str);
                    continue;
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                Message message = Message.obtain();
                message.obj = stringBuffer;
                handler1.sendMessage(message);
                return;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onClick(View paramView) {
        if (paramView.getId() == R.id.button) {
            (new Thread(new Runnable() {
                public void run() {
                    getwebinfo();
                }
            })).start();
        } else if (paramView.getId() == R.id.picBtn) {
            (new Thread(new Runnable() {
                public void run() {
                    try {
                        URL uRL = new URL("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png");
                        HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(5000);
                        Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                        Message message = Message.obtain();
                        message.obj = bitmap;
                        handler.sendMessage(message);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            })).start();
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_home_work_html);
        Button button = (Button)findViewById(R.id.button);
        this.tv = (TextView)findViewById(R.id.tv_content);
        this.et = (EditText)findViewById(R.id.editText);
        this.iv = (ImageView)findViewById(R.id.imageView);
        button.setOnClickListener(this);
        findViewById(R.id.picBtn).setOnClickListener(this);
        ActivityCompat.requestPermissions((Activity)this,
                new String[] { "android.permission.INTERNET" }, 1);
        this.handler = new Handler() {
            public void handleMessage(Message param1Message) {
                Bitmap bitmap = (Bitmap)param1Message.obj;
                iv.setImageBitmap(bitmap);
                tv.setText("");
            }
        };
        this.handler1 = new Handler() {
            public void handleMessage(Message param1Message) {
                StringBuffer stringBuffer = (StringBuffer)param1Message.obj;
                tv.setText(stringBuffer);
            }
        };
        findViewById(R.id.btn2location).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ((WebView)findViewById(R.id.wv)).loadUrl(et.getText().toString());
                tv.setText("");
            }
        });
    }
}
