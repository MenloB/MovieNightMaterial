package nikola.bottomnavigationview.Fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nikola.bottomnavigationview.Adapters.IconicAdapterTheater;
import nikola.bottomnavigationview.Model.Bioskop;
import nikola.bottomnavigationview.R;

/**
 * Created by Nikola on 4/21/2017.
 */

public class TheaterFragment extends android.support.v4.app.Fragment {

    private List<Bioskop> listaBioskopa = null;

    public TheaterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.second_fragment, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.listaBioskopa);
        rv.setHasFixedSize(true);

        listaBioskopa = new ArrayList<>();

        listaBioskopa.add(( new Bioskop("Cineplexx", "Mixtape bicycle rights ugh hoodie DIY. Ugh cronut yuccie flexitarian, " +
                "farm-to-table 8-bit etsy neutra sriracha pinterest 90's fashion axe meditation. " +
                "Brooklyn seitan kickstarter, chambray freegan pinterest waistcoat hexagon kitsch " +
                "flexitarian truffaut austin fashion axe irony. Succulents thundercats air plant blog, pok pok " +
                "blue bottle selfies viral direct trade fap. Next level irony kogi, air plant man braid semiotics " +
                "kickstarter 90's enamel pin meggings kitsch wolf austin artisan.")));

        listaBioskopa.add(( new Bioskop("Vilin Grad", "Mixtape bicycle rights ugh hoodie DIY. Ugh cronut yuccie flexitarian, " +
                "farm-to-table 8-bit etsy neutra sriracha pinterest 90's fashion axe meditation. " +
                "Brooklyn seitan kickstarter, chambray freegan pinterest waistcoat hexagon kitsch " +
                "flexitarian truffaut austin fashion axe irony. Succulents thundercats air plant blog, pok pok " +
                "blue bottle selfies viral direct trade fap. Next level irony kogi, air plant man braid semiotics " +
                "kickstarter 90's enamel pin meggings kitsch wolf austin artisan.")));

        listaBioskopa.add(( new Bioskop("Kupina", "Mixtape bicycle rights ugh hoodie DIY. Ugh cronut yuccie flexitarian, " +
                "farm-to-table 8-bit etsy neutra sriracha pinterest 90's fashion axe meditation. " +
                "Brooklyn seitan kickstarter, chambray freegan pinterest waistcoat hexagon kitsch " +
                "flexitarian truffaut austin fashion axe irony. Succulents thundercats air plant blog, pok pok " +
                "blue bottle selfies viral direct trade fap. Next level irony kogi, air plant man braid semiotics " +
                "kickstarter 90's enamel pin meggings kitsch wolf austin artisan.")));

        rv.setAdapter(new IconicAdapterTheater(getActivity(), listaBioskopa));

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return rootView;
    }
}
