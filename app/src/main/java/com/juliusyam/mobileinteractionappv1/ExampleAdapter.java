package com.juliusyam.mobileinteractionappv1;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private JSONArray mJsonArray;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTV;
        public TextView addressTV;
        public TextView crossStreetTV;
        public TextView distanceTV;
        public TextView postcodeTV;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nameTextView);
            addressTV = itemView.findViewById(R.id.addressTextView);
            crossStreetTV = itemView.findViewById(R.id.crossStreetTextView);
            distanceTV = itemView.findViewById(R.id.distanceTextView);
            postcodeTV = itemView.findViewById(R.id.postcodeTextView);
        }
    }

    public ExampleAdapter() {
        mJsonArray = new JSONArray();
    }

    public void updateJsonArray(JSONArray jsonArray) {

        this.mJsonArray = jsonArray;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);
        return exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        try {
            JSONObject currentItem = mJsonArray.getJSONObject(position);

            String nameParsed = currentItem.getString("name");

            JSONObject itemLocation = currentItem.getJSONObject("location");
            String addressParsed = itemLocation.optString("address");
            String crossStreetParsed = itemLocation.optString("crossStreet");
            String distanceParsed = itemLocation.getString("distance") + " metres";
            String postcodeParsed = itemLocation.optString("postalCode");

            holder.nameTV.setText(nameParsed);
            holder.addressTV.setText(addressParsed);
            holder.crossStreetTV.setText(crossStreetParsed);
            holder.distanceTV.setText(distanceParsed);
            holder.postcodeTV.setText(postcodeParsed);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

        //Setting length of objects I want to retrieve, a maximum of 6 or however many available if below 6
        return Math.min(6, mJsonArray.length());
    }
}
