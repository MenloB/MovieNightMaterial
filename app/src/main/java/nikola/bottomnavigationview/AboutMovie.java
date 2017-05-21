package nikola.bottomnavigationview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by Nikola on 5/12/2017.
 */

public class AboutMovie extends YouTubeBaseActivity {
    private JSONObject jsonObject = null;
    private JSONArray jsonArray = null;
    private static String trailerId = null;
    private YouTubePlayerView youTubePlayerView = null;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_movie);

        getTrailerId();

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(trailerId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Udaljeni servis nije u funkciji", Toast.LENGTH_SHORT).show();
            }
        };

        youTubePlayerView.initialize("AIzaSyBc7FHZk5P2zCZnTRyFz_x-cDSya9aUdfA", onInitializedListener);

    }

    public void getTrailerId()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://api.themoviedb.org/3/movie/" + getIntent().getStringExtra("IdFilma") + "/videos?api_key=8e20230f25939a349c2e37680cdaff95",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("results");

                            JSONObject trailer = jsonArray.getJSONObject(0);
                            //dummyCont.setText(trailer.getString("key"));

                            trailerId = trailer.getString("key");
                            Log.e("TRAILERID: ", trailerId);
                        }

                        catch (JSONException ex)
                        {
                            ex.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AboutMovie.this, "NE RADI IZ NEKOG RAZLOGA", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}
