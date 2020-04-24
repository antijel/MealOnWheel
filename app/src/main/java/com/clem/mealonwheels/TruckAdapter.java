package com.clem.mealonwheels;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TruckAdapter extends RecyclerView.Adapter<TruckAdapter.ViewHolder> implements View.OnClickListener{

    private List<Truck> trucks;
    private Context mContext;
    private String number;
    private String link;

    public TruckAdapter(Context context,List<Truck> trucks) {
        this.mContext = context;
        this.trucks = trucks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_truck, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Truck truck = trucks.get(position);

        if(truck.getImage().isEmpty()){
            holder.image.setImageResource(R.drawable.burger);
        }
        else{
            Picasso.with(mContext).load(truck.getImage()).placeholder(R.drawable.burger).into(holder.image);
        }

        number = truck.getNumber();
        link = truck.getLink();

        holder.name.setText(truck.getName());
        holder.location.setText(truck.getLocation());
        holder.time.setText(truck.getTime());
        holder.number.setText(truck.getNumber());
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(R.id.name,truck.getName());
        holder.itemView.setTag(R.id.link,truck.getLink());
        holder.itemView.setTag(R.id.number,truck.getNumber());
        //context = holder.itemView.getContext();


        if(truck.getTime().equals("Fermé")){
            holder.itemView.setBackgroundColor(0xFFF5BBAC);
            Log.i("COLOR", "COLOR : 0xFFF5BBAC");
        }else{
            holder.itemView.setBackgroundColor(0xFFCFF2D2);
            Log.i("COLOR", "COLOR : 0xFFCFF2D2");

        }

    }

    @Override
    public int getItemCount() {
        return trucks.size();
    }

    @Override
    public void onClick(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        Log.i("RESULT", "link : " + v.getTag(R.id.link).toString());
        Log.i("RESULT", "number : " + v.getTag(R.id.number));


        builder.setTitle("Contact: " + v.getTag(R.id.name) )
                .setPositiveButton("Appeler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Uri phone = Uri.parse("tel:"+v.getTag(R.id.number));
                        Intent callIntent = new Intent(Intent.ACTION_DIAL,phone);
                        v.getContext().startActivity(callIntent);

                    }
                })
                .setNegativeButton("Website", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Uri url = Uri.parse(v.getTag(R.id.link).toString());
                        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, url) ;

                        v.getContext().startActivity(websiteIntent);

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();


        //Log.i("RESULT", "onClick: "+ link +" / "+number);

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView image;
        final TextView name;
        final TextView location;
        final TextView time;
        final TextView number;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.NameTextView);
            location = itemView.findViewById(R.id.AdressTextView);
            time = itemView.findViewById(R.id.TimeTextView);
            number = itemView.findViewById(R.id.NumberTextView);
        }
    }


}
