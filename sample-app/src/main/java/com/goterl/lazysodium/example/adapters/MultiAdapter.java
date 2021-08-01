package com.goterl.lazysodium.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.goterl.lazysodium.example.R;
import com.goterl.lazysodium.example.models.Operation;

import java.util.List;

public class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.ViewHolder> {

    private List<Operation> data;
    private boolean isCredits;
    private LayoutInflater inflator;
    private ItemClickListener clickListener;

    public MultiAdapter(Context context, List<Operation> data, boolean isCredits) {
        this.inflator = LayoutInflater.from(context);
        this.data = data;
        this.isCredits = isCredits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (isCredits) {
            view = inflator.inflate(R.layout.credits_item, parent, false);
        } else {
            view = inflator.inflate(R.layout.operation_item, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Operation operation = data.get(position);
        holder.title.setText(operation.getTitle());
        holder.desc.setText(operation.getDesc());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView desc;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public Operation getItem(int id) {
        return data.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
