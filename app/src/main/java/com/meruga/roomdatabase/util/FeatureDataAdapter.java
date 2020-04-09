package com.meruga.roomdatabase.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meruga.roomdatabase.R;
import com.meruga.roomdatabase.model.FeatureData;
import com.meruga.roomdatabase.view.UpdateDataActivity;

import java.util.List;

/**
 * @author Mehareesh.M
 *      We will read the saved task from the database, and we will display it on the RecyclerView.
 */
public class FeatureDataAdapter extends RecyclerView.Adapter<FeatureDataAdapter.FeatureDataViewHolder> {

    private Context mContext;
    private List<FeatureData> mFeaturesList;

    public FeatureDataAdapter(Context mCtx, List<FeatureData> featureList) {
        this.mContext = mCtx;
        this.mFeaturesList = featureList;
    }

    @NonNull
    @Override
    public FeatureDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_data, parent, false);
        return new FeatureDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureDataViewHolder holder, int position) {
        FeatureData featureData = mFeaturesList.get(position);
        holder.textViewType.setText(featureData.getType());
        holder.textViewName.setText(featureData.getName());
    }

    @Override
    public int getItemCount() {
        return mFeaturesList.size();
    }

    class FeatureDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewType;
        TextView textViewName;

        FeatureDataViewHolder(View itemView) {
            super(itemView);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewName = itemView.findViewById(R.id.textName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            FeatureData featureData = mFeaturesList.get(getAdapterPosition());
            Intent intent = new Intent(mContext, UpdateDataActivity.class);
            intent.putExtra("featureData", featureData);
            mContext.startActivity(intent);
        }
    }
}
