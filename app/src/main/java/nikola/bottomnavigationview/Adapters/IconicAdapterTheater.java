package nikola.bottomnavigationview.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nikola.bottomnavigationview.Model.Bioskop;
import nikola.bottomnavigationview.R;

/**
 * Created by Nikola on 4/27/2017.
 */

public class IconicAdapterTheater extends RecyclerView.Adapter<IconicAdapterTheater.RowHolderString> {
    private Context context;
    private List<Bioskop> mDataset;

    public IconicAdapterTheater(Context context, List<Bioskop> mDataset) {
        this.context  = context;
        this.mDataset = mDataset;
    }

    @Override
    public IconicAdapterTheater.RowHolderString onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theater, parent, false);
        IconicAdapterTheater.RowHolderString rh = new IconicAdapterTheater.RowHolderString(v);
        return rh;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onBindViewHolder(IconicAdapterTheater.RowHolderString holder, int position) {
        holder.titleBioskop.setText(mDataset.get(position).getName());
        holder.descBioskop.setText(mDataset.get(position).getDesc());
        Picasso.with(context).load(R.drawable.theater).into(holder.posterBioskop);

    }

    public static class RowHolderString extends RecyclerView.ViewHolder {
        private ImageView posterBioskop;
        private TextView descBioskop;
        private TextView titleBioskop;

        public RowHolderString(View v) {
            super(v);

            posterBioskop = (ImageView) v.findViewById(R.id.posterBioskop);
            descBioskop   = (TextView) v.findViewById(R.id.descBioskop);
            titleBioskop  = (TextView) v.findViewById(R.id.titleBioskop);
        }
    }
}
