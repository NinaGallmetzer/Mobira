package swe.mobira.entities.ringingrecord;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import swe.mobira.R;

public class RingingRecordAdapter extends RecyclerView.Adapter<RingingRecordAdapter.RingingRecordHolder> {
    private List<RingingRecord> ringingRecords = new ArrayList<>();

    private OnItemClickListener listener;

    public void setRingingRecords(List<RingingRecord> ringingRecords) {
        this.ringingRecords = ringingRecords;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ringingRecords.size();
    }

    @NonNull
    @Override
    public RingingRecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ringing_record_item, parent, false);
        return new RingingRecordHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RingingRecordHolder holder, int position) {
        RingingRecord currentRingingRecord = ringingRecords.get(position);
        holder.textViewDate.setText(currentRingingRecord.getRecordDate());
        holder.textViewStartTime.setText(currentRingingRecord.getStartTime());
        holder.textViewEndTime.setText(currentRingingRecord.getEndTime());
    }

    class RingingRecordHolder extends RecyclerView.ViewHolder {
        private final TextView textViewDate;
        private final TextView textViewStartTime;
        private final TextView textViewEndTime;
        public RingingRecordHolder(View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.text_view_record_item_date);
            textViewStartTime = itemView.findViewById(R.id.text_view_record_item_start_time);
            textViewEndTime = itemView.findViewById(R.id.text_view_record_item_end_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(ringingRecords.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RingingRecord ringingRecord);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
