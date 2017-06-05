package nikola.bottomnavigationview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import nikola.bottomnavigationview.CustomControls.CustomCircleBar;
import nikola.bottomnavigationview.Model.Projekcija;

/**
 * Created by Nikola on 5/12/2017.
 */

public class AboutMovie extends YouTubeBaseActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private JSONObject jsonObject               = null;
    private JSONArray jsonArray                 = null;
    private static String trailerId             = null;
    private YouTubePlayerView youTubePlayerView = null;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private TextView title_movie                = null;
    private TextView desc_movie                 = null;

    private CustomCircleBar rate                = null;
    private CustomCircleBar rate1               = null;
    private CustomCircleBar rate2               = null;

    private Button rezervisi                    = null;
    private Button rezervisiBtn                 = null;
    private ImageButton addFavorite             = null;

    private LinearLayout rezervisiLayout        = null;
    private boolean rezervacijeOtvorene         = false;

    private Spinner spiner                      = null;
    private String  odabranBioskop              = null;
    private String vremeProjekcije              = null;
    private int preostaloMesta                  = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_movie);

        rezervisiLayout = (LinearLayout) findViewById(R.id.rezervisiLayout);
        spiner          = (Spinner) findViewById(R.id.bioskopi);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.bioskopiDropDownMenu, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(arrayAdapter);
        spiner.setOnItemSelectedListener(this);

        title_movie = (TextView) findViewById(R.id.title_movie);
        desc_movie  = (TextView) findViewById(R.id.desc_movie);

        title_movie.setText(getIntent().getStringExtra("Title"));
        desc_movie.setText(getIntent().getStringExtra("DescFilma"));

        rate  = (CustomCircleBar) findViewById(R.id.rateBar1);
        rate.setProgressWithAnimation(64);
        rate1 = (CustomCircleBar) findViewById(R.id.rateBar2);
        rate1.setProgressWithAnimation(100);
        rate2 = (CustomCircleBar) findViewById(R.id.rateBar3);
        rate2.setProgressWithAnimation(46);

        rezervisi    = (Button) findViewById(R.id.rezervisi);
        rezervisiBtn = (Button) findViewById(R.id.rezervisiBtn);

        rezervisiBtn.setOnClickListener(this);

        rezervisi.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!rezervacijeOtvorene) {
                    rezervisiLayout.setVisibility(View.VISIBLE);
                    rezervacijeOtvorene = true;
                } else {
                    rezervisiLayout.setVisibility(View.GONE);
                    rezervacijeOtvorene = false;
                }
            }
        });

        addFavorite = (ImageButton) findViewById(R.id.dodajUFavorite);

        addFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.movieButtons), "Added to Favorites to be implemented", Snackbar.LENGTH_SHORT).show();
            }
        });

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        odabranBioskop = parent.getAdapter().getItem(position).toString();
        Toast.makeText(this, "Selected: " + parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(AboutMovie.this);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        preostaloMesta -= 1;
        Projekcija projekcija = new Projekcija(vremeProjekcije, preostaloMesta, odabranBioskop);
        databaseReference.child("projekcije").child(odabranBioskop).child(MainActivity.punoIme).setValue(projekcija);
        Snackbar.make(findViewById(R.id.movieButtons), "Rezervisano u bioskopu: " + odabranBioskop + " za projekciju u: " + vremeProjekcije, Snackbar.LENGTH_LONG).show();
    }

    public void onRadioButtonChecked(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.radio17h:
                vremeProjekcije = "17h";
                Toast.makeText(this, "Rezervacija za 17h", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio20h:
                vremeProjekcije = "20h";
                Toast.makeText(this, "Rezervacija za 20h", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio22h:
                vremeProjekcije = "22h";
                Toast.makeText(this, "Rezervacija za 22h", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
