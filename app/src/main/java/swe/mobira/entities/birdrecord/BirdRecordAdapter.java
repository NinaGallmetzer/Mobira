package swe.mobira.entities.birdrecord;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import swe.mobira.R;

public class BirdRecordAdapter extends RecyclerView.Adapter<BirdRecordAdapter.BirdRecordHolder> {
    private List<BirdRecord> birdRecords = new ArrayList<>();

    private BirdRecordAdapter.OnItemClickListener listener;

    public void setBirdRecords(List<BirdRecord> birdRecords) {
        this.birdRecords = birdRecords;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return birdRecords.size();
    }

    @NonNull
    @Override
    public BirdRecordAdapter.BirdRecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bird_record_item, parent, false);
        return new BirdRecordAdapter.BirdRecordHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BirdRecordAdapter.BirdRecordHolder holder, int position) {
        BirdRecord currentBirdRecord = birdRecords.get(position);
        holder.textViewSpecies.setText(currentBirdRecord.getSpecies());
        holder.textViewTime.setText(currentBirdRecord.getTime());
        holder.textViewNet.setText(String.valueOf(currentBirdRecord.getNetNumber()));
        holder.textViewAge.setText(String.valueOf(currentBirdRecord.getAge()));
        holder.textViewSex.setText(String.valueOf(currentBirdRecord.getSex()));
    }

    class BirdRecordHolder extends RecyclerView.ViewHolder {
        private TextView textViewSpecies;
        private TextView textViewTime;
        private TextView textViewNet;
        private TextView textViewAge;
        private TextView textViewSex;

        public BirdRecordHolder(View itemView) {
            super(itemView);
            textViewSpecies = itemView.findViewById(R.id.text_view_species);
            textViewTime = itemView.findViewById(R.id.text_view_time);
            textViewNet = itemView.findViewById(R.id.text_view_net);
            textViewAge = itemView.findViewById(R.id.text_view_age);
            textViewSex = itemView.findViewById(R.id.text_view_sex);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(birdRecords.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(BirdRecord birdRecord);
    }

    public void setOnItemClickListener(BirdRecordAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
