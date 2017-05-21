package nikola.bottomnavigationview.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import nikola.bottomnavigationview.AboutMovie;
import nikola.bottomnavigationview.MainActivity;
import nikola.bottomnavigationview.Model.Film;
import nikola.bottomnavigationview.R;

/**
 * Created by Nikola on 4/27/2017.
 */

public class IconicAdapter extends RecyclerView.Adapter<IconicAdapter.RowHolder> {

    private Context context;
    private List<Film> mDataset;

    public IconicAdapter(Context context, List<Film> mDataset) {
        this.context  = context;
        this.mDataset = mDataset;
    }

    @Override
    public RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        RowHolder rh = new RowHolder(v);
        return rh;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onBindViewHolder(RowHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getTitle());
        holder.desc.setText(mDataset.get(position).getDescription());
        holder.rate.setText(mDataset.get(position).getRate() + "/10");
        holder.videoId.setText(mDataset.get(position).getTrailerId());
        Picasso.with(context).load(mDataset.get(position).getPoster()).into(holder.poster);
    }

    public static class RowHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView desc;
        private ImageView poster;
        private TextView rate;
        private TextView videoId;
        private Button moreBtn;

        private final Context context;

        public RowHolder(View v) {
            super(v);
            context = v.getContext();

            title  = (TextView) v.findViewById(R.id.title);
            desc   = (TextView) v.findViewById(R.id.desc);
            poster = (ImageView) v.findViewById(R.id.poster);
            rate   = (TextView) v.findViewById(R.id.rate);
            videoId = (TextView) v.findViewById(R.id.idFilma);
            moreBtn = (Button) v.findViewById(R.id.moreBtn);

            moreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent movieIntent = new Intent(context, AboutMovie.class);
                    movieIntent.putExtra("Title", title.getText());
                    movieIntent.putExtra("IdFilma", videoId.getText());
                    context.startActivity(movieIntent);
                }
            });
        }
    }

}