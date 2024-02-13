package com.example.recyclerviewdemo.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.databinding.LayoutTuneItemBinding;
import com.example.recyclerviewdemo.model.Tune;

import java.util.List;

public class TuneAdapter extends RecyclerView.Adapter<TuneAdapter.TuneViewHolder> {

    List<Tune> adapterTuneList;
    int SelectedInd = -1; // corresponds to selected tune

    public int getSelectedInd() {
        return SelectedInd;
    }

    public void setSelectedInd(int selectedInd) {
        SelectedInd = selectedInd;
        notifyDataSetChanged(); // refreshes the tune list
    }

    public List<Tune> getAdapterTuneList() {
        return adapterTuneList;
    }

    public void setAdapterTuneList(List<Tune> adapterTuneList) {
        this.adapterTuneList = adapterTuneList;
        notifyDataSetChanged(); // refreshes the tune list
    }

    public TuneAdapter(List<Tune> adapterTuneList) {
        this.adapterTuneList = adapterTuneList;
    }

    public class TuneViewHolder extends RecyclerView.ViewHolder {
        LayoutTuneItemBinding holderBinding;

        public TuneViewHolder(@NonNull View itemView, LayoutTuneItemBinding holderBinding) {
            super(itemView);
            this.holderBinding = holderBinding;
            // Set up click listeners

            // click listener for whole item - independent click listener
            this.holderBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holderBinding.getRoot().setBackgroundColor(Color.LTGRAY);
                }
            });

            // dependent click listener
            this.holderBinding.imgViewPlayPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getAdapterPosition() == SelectedInd) {
                        setSelectedInd(-1);
                    } else {
                        setSelectedInd(getAdapterPosition());
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public TuneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create Item Binding object
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutTuneItemBinding binding = LayoutTuneItemBinding.inflate(inflater, parent, false);
        // Use the object, to create TuneViewHolder object
        TuneViewHolder holder = new TuneViewHolder(binding.getRoot(), binding);

        // return TuneViewHolder object
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuneViewHolder holder, int position) {
        holder.holderBinding.txtViewTune.setText(adapterTuneList.get(position).getTuneName());
        holder.holderBinding.imgViewTune.setImageResource(adapterTuneList.get(position).getTunePic());
        if(position == SelectedInd)
            holder.holderBinding.imgViewPlayPause.setImageResource(R.drawable.pause);
        else
            holder.holderBinding.imgViewPlayPause.setImageResource(R.drawable.play);
    }

    @Override
    public int getItemCount() {
        return adapterTuneList.size();
    }
}
