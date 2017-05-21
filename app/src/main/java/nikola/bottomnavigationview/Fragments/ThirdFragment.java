package nikola.bottomnavigationview.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nikola.bottomnavigationview.R;

/**
 * Created by Nikola on 4/21/2017.
 */

public class ThirdFragment extends android.support.v4.app.Fragment {

    public ThirdFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.third_fragment, parent, false);
    }
}
