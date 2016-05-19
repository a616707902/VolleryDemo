package com.example.chenpan.volleydemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.chenpan.volleydemo.test.BitmapCache;
import com.example.chenpan.volleydemo.test.VolleyInterface;
import com.example.chenpan.volleydemo.test.VolleyRequest;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button mBtnGet;
    private Button mBthPost;
    private Button mBtnImg;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.resText);
        mBtnGet = (Button) findViewById(R.id.get);
        mBthPost = (Button) findViewById(R.id.post);
        mBtnImg = (Button) findViewById(R.id.loadimg);
        mBtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // loadImage();
                loadImage2();
            }
        });
        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyGet();
            }
        });
        mBthPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyPost();
            }
        });
    }

    private void loadImage2() {
        String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        ImageLoader imageLoader=new ImageLoader(MyApplication.getQueues(),new BitmapCache());
        ImageLoader.ImageListener imageListener=ImageLoader.getImageListener(imageView,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        imageLoader.get(url,imageListener);
    }

    private void loadImage() {
        String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {//0,0是原图，100，压缩
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
        imageRequest.setTag("getimg");
        MyApplication.getQueues().add(imageRequest);
    }

    private void volleyGet() {
        String url = "http://apis.juhe.cn/mobile/get?phone=18200366005&key=e59b086b3ef990049b3056d6a5d5e342";
       /* StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });*/
       /* JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, "", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                textView.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag("get");
        MyApplication.getQueues().add(request);*/
        VolleyInterface volleyInterface = new VolleyInterface(this) {
            @Override
            public void onMySuccess(String result) {
                textView.setText(result);
            }

            @Override
            public void onMyError(VolleyError result) {
                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        VolleyRequest.RequestGetString(this, url, "get", volleyInterface);


    }

    private void volleyPost() {
        String url = "http://apis.juhe.cn/mobile/get";
      /*  StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String > map=new HashMap<String,String>();
                map.put("phone","18613009128");
                map.put("key","e59b086b3ef990049b3056d6a5d5e342");
                return map;
            }
        };*/
       /* Map<String ,String > map=new HashMap<String,String>();
        map.put("phone","18613009128");
        map.put("key","e59b086b3ef990049b3056d6a5d5e342");
        JSONObject jsonObject=new JSONObject(map);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                textView.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag("get");
        MyApplication.getQueues().add(request);*/
        VolleyInterface volleyInterface = new VolleyInterface(this) {
            @Override
            public void onMySuccess(String result) {
                textView.setText(result);
            }

            @Override
            public void onMyError(VolleyError result) {
                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        Map<String, String> map = new HashMap<String, String>();
        map.put("phone", "18613009128");
        map.put("key", "e59b086b3ef990049b3056d6a5d5e342");
        VolleyRequest.RequestPostString(this, url, "get", map, volleyInterface);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getQueues().cancelAll("get");
        MyApplication.getQueues().cancelAll("getimg");
    }
}
