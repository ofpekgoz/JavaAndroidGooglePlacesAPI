package com.omerfpekgoz.uygulama_googleplacesapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omerfpekgoz.uygulama_googleplacesapi.R;
import com.omerfpekgoz.uygulama_googleplacesapi.model.Places;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.cardViewHolder>{

    private Context mContext;
    private List<Places> placesList;

    public PlaceAdapter() {
    }

    public PlaceAdapter(Context mContext, List<Places> placesList) {
        this.mContext = mContext;
        this.placesList = placesList;
    }

    @NonNull
    @Override
    public cardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_places_tasarim,parent,false);

        return new cardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardViewHolder holder, int position) {

        Places places=placesList.get(position);

        holder.txtPlaceName.setText(places.getPlaceName());
        holder.txtPlaceLocation.setText(places.getPlaceLatitude().toString()+" - "+places.getPlaceLongitude().toString());
        holder.txtPlaceVicinity.setText(places.getPlaceVicinity().toString());

        String url=places.getPlaceIcon();
        Picasso.with(mContext).load(url).resize(120,120)
                .into(holder.iconPlace);

    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }


    public class cardViewHolder extends RecyclerView.ViewHolder {

        private TextView txtPlaceName,txtPlaceLocation,txtPlaceVicinity;
        private ImageView iconPlace;

        public cardViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPlaceName=itemView.findViewById(R.id.txtPlaceName);
            txtPlaceLocation=itemView.findViewById(R.id.txtPlaceLocation);
            txtPlaceVicinity=itemView.findViewById(R.id.txtPlaceVicinity);
            iconPlace=itemView.findViewById(R.id.iconPlace);

        }
    }
}
