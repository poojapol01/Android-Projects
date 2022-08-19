package com.example.sharememe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    //private Button btnRequest;

    //private RequestQueue mRequestQueue;
    private JsonObjectRequest jsonObjectRequest;
    ImageView memeImgView;
    ProgressBar progressBar;
    private String mUrlString = "https://meme-api.herokuapp.com/gimme";
    String currentImgURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        memeImgView = (ImageView) findViewById(R.id.memeImageView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        LoadMeme();
    }
    private void LoadMeme(){
        //Initially show the Progress Bar view.
        progressBar.setVisibility(View.VISIBLE);

        //RequestQueue initialized
        //mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            //String url = getResources().getString(R.string.url);
            //JSONObject object = new JSONObject();

            //String Request initialized
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mUrlString, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        currentImgURL = response.getString("url");
                        Glide.with(getApplicationContext()).load(currentImgURL)
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        // log exception
                                        Log.e("TAG", "Error loading image", e);
                                        return false; // important to return false so the error placeholder can be placed
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        //hide the Progress Bar view.
                                        progressBar.setVisibility(View.INVISIBLE);
                                        return false;
                                    }
                                })
                                .into(memeImgView);
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
            //mRequestQueue.add(jsonObjectRequest);
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ShareMeme(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"Hey, Checkout out this cool meme " +currentImgURL);
        startActivity(Intent.createChooser(intent,"Share via"));
    }

    public void NextMeme(View view) {
        LoadMeme();
    }
}