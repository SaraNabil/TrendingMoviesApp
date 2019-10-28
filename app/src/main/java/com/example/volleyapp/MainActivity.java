package com.example.volleyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.moviesRv)
    RecyclerView moviesRv;
    @BindView(R.id.loading)
    ProgressBar loading;

    ArrayList<MovieModel> moviesList;
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        moviesList = new ArrayList<>();
        adapter = new MovieAdapter(this, moviesList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        moviesRv.setAdapter(adapter);
        moviesRv.setLayoutManager(layoutManager);

        getMoviesData();

    }

    private void getMoviesData() {

        //https://api.themoviedb.org/3/trending/{media_type}/{time_window}?api_key
        String url = Constants.BASE_URL + "/movie" + "/day?" + Constants.API_KEY;

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                loading.setVisibility(View.GONE);
                try {
                    JSONArray movies = response.getJSONArray("results");
                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject movieObj = movies.getJSONObject(i);
                        MovieModel model = new MovieModel();
                        model.setId(movieObj.getInt("id"));
                        model.setMovieName(movieObj.getString("title"));
                        model.setMovieImageLink(movieObj.getString("backdrop_path"));
                        model.setPopularity(movieObj.getDouble("popularity"));

                        moviesList.add(model);
                    }

                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("MyProject", error.toString());
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(objectRequest);
    }
}
