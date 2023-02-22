package swe.mobira;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import swe.mobira.entities.Site;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppHolder> {
    private List<Site> sites = new ArrayList<>();

    // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
    // take OnItemClickListener from own package!!
    private OnItemClickListener listener;

    @NonNull
    @Override
    public AppHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.site_item, parent, false);
        return new AppHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppHolder holder, int position) {
        Site currentSite = sites.get(position);
        holder.textViewTitle.setText(currentSite.getTitle());
        holder.textViewDescription.setText(currentSite.getDescription());
//      holder.textViewLatitude.setText(Double.toString(currentSite.getLatitude()));
//      holder.textViewLongitude.setText(Double.toString(currentSite.getLongitude()));
//      holder.textViewComment.setText(currentSite.getComment());
    }

    @Override
    public int getItemCount() {
        return sites.size();
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
        notifyDataSetChanged();
    }

    class AppHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
//      private TextView textViewLatitude;
//      private TextView textViewLongitude;
//      private TextView textViewComment;
        public AppHolder(View itemview) {
            super(itemview);
            textViewTitle = itemview.findViewById(R.id.text_view_title);
            textViewDescription = itemview.findViewById(R.id.text_view_description);
//          textViewLatitude = itemview.findViewById(R.id.text_view_latitude);
//          textViewLongitude = itemview.findViewById(R.id.text_view_longitude);
//          textViewComment = itemview.findViewById(R.id.text_view_comment);

            // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
            // create new OnClickListener, get position from adapter
            // and pass corresponding site to listener
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    // make sure we haven't clicked on a site that doesn't exit (e.g. deleted)
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(sites.get(position));
                    }
                }
            });
        }
    }

    // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
    // create ClickListener for Items of Card view (sites) > on click, site gets passed to next step
    public interface OnItemClickListener {
        void onItemClick(Site site);
    }

    // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
    // take OnItemClickListener from own package!!
    // use listener to call onItemClick method and pass selected Site
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
