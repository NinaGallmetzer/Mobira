package swe.mobira.entities.site;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import swe.mobira.R;

// RECYCLERVIEW + ADAPTER (https://www.youtube.com/watch?v=reSPN7mgshI)
// RecyclerView is kind of like a loop for cards (creates cards/site in a loop) ??
// adapter handles click-on-item-events (extracts information from item that has been clicked)
// .SiteHolder to indicate which view holder to used
public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.SiteHolder> {
    private List<Site> sites = new ArrayList<>();

    // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
    // take OnItemClickListener from own package!!
    private OnItemClickListener listener;

    // RECYCLERVIEW + ADAPTER (https://www.youtube.com/watch?v=reSPN7mgshI)
    // get list of notes into RecyclerView and
    // update adapter, in general don't use notifyDataSetChanged in RecyclerView > see Video 8
    public void setSites(List<Site> sites) {
        this.sites = sites;
        notifyDataSetChanged();
    }

    // RECYCLERVIEW + ADAPTER (https://www.youtube.com/watch?v=reSPN7mgshI)
    // number of sites to display (all)
    @Override
    public int getItemCount() {
        return sites.size();
    }

    // RECYCLERVIEW + ADAPTER (https://www.youtube.com/watch?v=reSPN7mgshI)
    // create and return NoteHolder, parent = RecyclerView itself
    @NonNull
    @Override
    public SiteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.site_item, parent, false);
        return new SiteHolder(itemView);
    }

    // RECYCLERVIEW + ADAPTER (https://www.youtube.com/watch?v=reSPN7mgshI)
    // getting data from single site java objects (individual cards) into views of SiteHolder
    // position defines position of the card in the list of cards (later used to identify card if clicked on)
    // get position of individual site > put attributes of site into SiteHolder
    // in case of non-string-values > ...setText(String.valueOf(...)
    @Override
    public void onBindViewHolder(@NonNull SiteHolder holder, int position) {
        Site currentSite = sites.get(position);
        holder.textViewTitle.setText(currentSite.getTitle());
        holder.textViewDescription.setText(currentSite.getDescription());
    }

    // RECYCLERVIEW + ADAPTER (https://www.youtube.com/watch?v=reSPN7mgshI)
    // holds data for recyclerview (list of cards/sites)
    class SiteHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescription;
        // RECYCLERVIEW + ADAPTER (https://www.youtube.com/watch?v=reSPN7mgshI)
        // connects data and xml (itemView = site.item.xml)
        public SiteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_site_title);
            textViewDescription = itemView.findViewById(R.id.text_view_site_description);

            // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
            // create new OnClickListener, get position from adapter
            // and pass corresponding site to listener
            itemView.setOnClickListener(new View.OnClickListener() {
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
