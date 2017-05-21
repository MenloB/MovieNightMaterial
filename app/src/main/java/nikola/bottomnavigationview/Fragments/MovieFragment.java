package nikola.bottomnavigationview.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nikola.bottomnavigationview.AboutMovie;
import nikola.bottomnavigationview.Adapters.IconicAdapter;
import nikola.bottomnavigationview.Model.Film;
import nikola.bottomnavigationview.R;

/**
 * Created by Nikola on 4/21/2017.
 */

public class MovieFragment extends android.support.v4.app.Fragment {

    /*private static final String[] items={"lorem", "ipsum", "dolor",
            "sit", "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"};*/

    private List<Film> items;
    private RecyclerView rv = null;
    //public static final String apiURL = "https://api.themoviedb.org/4/list/10?page=1&api_key=8e20230f25939a349c2e37680cdaff95&sort_by=release_date.asc";
    //public static final String apiURL = "http://api.themoviedb.org/3/movie/upcoming?api_key=8e20230f25939a349c2e37680cdaff95&sort_by=release_date.asc";
    public static final String apiURL = "http://api.themoviedb.org/3/movie/now_playing?api_key=8e20230f25939a349c2e37680cdaff95&sort_by=release_date.asc";
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    public MovieFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.first_fragment, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.lista);
        rv.setHasFixedSize(true);

        items = new ArrayList<Film>();

        RequestQueue queue = Volley.newRequestQueue(this.getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getContext(), "RADI", Toast.LENGTH_SHORT).show();

                        try {


                            jsonObject = new JSONObject(response);
                            jsonArray  = jsonObject.getJSONArray("results");
                            int i = 0;

                            while(i < jsonArray.length()) {
                                JSONObject movie = jsonArray.getJSONObject(i);

                                Film movieInstance = new Film(movie.getString("id"), movie.getString("original_title"), movie.getString("overview"), "https://image.tmdb.org/t/p/w500" + movie.getString("poster_path"), movie.getString("vote_average"));
                                items.add(movieInstance);
                                Log.e("FILMOVIIIIIIIIIIIIIII: ", items.get(i).toString());
                                i ++ ;
                            }

                            Log.e("NOTIFYDATASETCHANGED: ", rv.getAdapter().toString());
                            rv.getAdapter().notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "NE RADI: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

        IconicAdapter ia = new IconicAdapter(getActivity(), getMovies());
        rv.setAdapter(ia);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }

    public List<Film> getMovies() {
        return items;
    }
}


