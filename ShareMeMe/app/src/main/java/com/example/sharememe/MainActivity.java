package com.example.sharememe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    //private Button btnRequest;

    private RequestQueue mRequestQueue;
    private JsonObjectRequest jsonObjectRequest;
    ImageView memeImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memeImgView = (ImageView) findViewById(R.id.memeImageView);

        LoadMeme();
    }
    private void LoadMeme(){
        String url = "https://meme-api.herokuapp.com/gimme";

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            //String url = getResources().getString(R.string.url);
            JSONObject object = new JSONObject();
            //String Request initialized
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String url = response.getString("url");
                        Glide.with(getApplicationContext()).load(url).into(memeImgView);
                        Log.d(TAG,"Response :" + response.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    Log.i(TAG,"Error :" + error.toString());
                }
            });
            mRequestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ShareMeme(View view) {
    }

    public void NextMeme(View view) {
    }
}