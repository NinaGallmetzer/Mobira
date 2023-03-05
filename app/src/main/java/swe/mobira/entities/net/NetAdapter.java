package swe.mobira.entities.net;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import swe.mobira.R;

public class NetAdapter extends RecyclerView.Adapter<NetAdapter.NetHolder> {
    private List<Net> nets = new ArrayList<>();

    private OnItemClickListener listener;

    public void setNets(List<Net> net) {
        this.nets = net;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return nets.size();
    }

    @NonNull
    @Override
    public NetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ringing_record_item, parent, false);
        return new NetHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NetHolder holder, int position) {
        Net currentNet = nets.get(position);
        holder.textViewNetNumber.setText(currentNet.getNetNumber());
        holder.textViewDescription.setText(currentNet.getDescription());
    }

    class NetHolder extends RecyclerView.ViewHolder {
        private TextView textViewNetNumber;
        private TextView textViewDescription;
        public NetHolder(View itemView) {
            super(itemView);
            textViewNetNumber = itemView.findViewById(R.id.text_view_net_number);
            textViewDescription = itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(nets.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Net ringingRecord);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
