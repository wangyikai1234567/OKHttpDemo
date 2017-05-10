package test.bwie.com.okhttpdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String uri = "http://api.kkmh.com/v1/daily/comic_lists/0?since=0&gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3ODI4ODU4NjAwLCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjIuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjM0IiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6InNhbXN1bmciLCJGcm9tSG9tZXBhZ2VVcGRhdGVEYXRlIjowLCIkc2NyZWVuX2hlaWdodCI6NTc2LCJIb21lcGFnZVVwZGF0ZURhdGUiOjAsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6NDYsIiRzY3JlZW5fd2lkdGgiOjEwMjQsIiRvcyI6IkFuZHJvaWQiLCJUcmlnZ2VyUGFnZSI6IkhvbWVQYWdlIiwiJGNhcnJpZXIiOiJDTUNDIiwiJG1vZGVsIjoiR1QtUDUyMTAiLCIkYXBwX3ZlcnNpb24iOiIzLjguMSJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6OTA1MTA0Mjc2Mzc1NTEwOSIsIm9yaWdpbmFsX2lkIjoiQTo5MDUxMDQyNzYzNzU1MTA5IiwiZXZlbnQiOiJSZWFkSG9tZVBhZ2UifQ%3D%3D";
    private String urii = "http://admin.wap.china.com/user/NavigateTypeAction.do";
    private Button mGett;
    private Button mGety;
    private Button mPostt;
    private Button mPosty;
    private TextView mTextView;
    private String mX;
    private String mXx;
    private OkHttpClient okHttpClient = new OkHttpClient();  //创建okHttpClient对象
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String xxx = (String) msg.obj;
                mTextView.setText(xxx);

            } else if (msg.what == 1) {
                String sss = (String) msg.obj;
                mTextView.setText(sss);

            } else if (msg.what == 2) {
                String aaa = (String) msg.obj;
                mTextView.setText(aaa);
            } else {
                String qqq = (String) msg.obj;
                mTextView.setText(qqq);
            }
        }
    };
    private String mSs;
    private Request mRequest;
    private FormBody mBody;
    private Request mRequest1;
    private String mLlllll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mGett = (Button) findViewById(R.id.tget);
        mGety = (Button) findViewById(R.id.yget);
        mPostt = (Button) findViewById(R.id.tpost);
        mPosty = (Button) findViewById(R.id.ypost);
        mTextView = (TextView) findViewById(R.id.tv);

        mGett.setOnClickListener(this);
        mGety.setOnClickListener(this);
        mPostt.setOnClickListener(this);
        mPosty.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tget:
                //获取对象 Request （Request 是okhttp中访问请求builder是一个辅助类）；
                gett(uri);
                //
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //response
                            Response response = okHttpClient.newCall(mRequest).execute();
                            //获取结果
                            mXx = response.body().string();
                            Message message = new Message();
                            message.what = 0;
                            message.obj = mXx;
                            mHandler.sendMessage(message);
                            response.body().close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


                break;
            case R.id.yget:

                //异步get
                gett(uri);

                okHttpClient.newCall(mRequest).enqueue(new Callback() {
                    //失败
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    //成功
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        mX = response.body().string();
                        Message messs = new Message();
                        messs.what = 2;
                        messs.obj = mX;
                        mHandler.sendMessage(messs);
                    }
                });
                break;
            case R.id.tpost:
                //同步post
                mBody = new FormBody.Builder()
                        .add("page", "1")
                        .add("code", "news")
                        .add("pageSize", "20")
                        .add("parentid", "0")
                        .add("type", "1")
                        .build();
                postt(urii);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Response response = okHttpClient.newCall(mRequest1).execute();
                            mSs = response.body().string();
                            response.body().close();
                            Message mes = new Message();
                            mes.what = 1;
                            mes.obj = mSs;
                            mHandler.sendMessage(mes);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;

            case R.id.ypost:
                //异步post
                mBody = new FormBody.Builder()
                        .add("page", "1")
                        .add("code", "news")
                        .add("pageSize", "20")
                        .add("parentid", "0")
                        .add("type", "1")
                        .build();
                postt(urii);
                okHttpClient.newCall(mRequest1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        mLlllll = response.body().string();
                        Message messs = new Message();
                        messs.what = 3;
                        messs.obj = mLlllll;
                        mHandler.sendMessage(messs);
                    }
                });


                break;

        }
    }

    private void postt(String uriii) {

        mRequest1 = new Request.Builder()
                .url(uriii)
                .post(mBody)
                .build();
    }

    private void gett(String uriii) {

        mRequest = new Request.Builder()
                .url(uriii)
                .build();
    }
}
