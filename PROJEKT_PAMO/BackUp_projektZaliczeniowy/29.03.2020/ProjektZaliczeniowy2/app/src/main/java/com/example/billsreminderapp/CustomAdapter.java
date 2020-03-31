package com.example.billsreminderapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Faktura> implements View.OnClickListener{

    private ArrayList<Faktura> faktury;
    Context mContext;
    Activity mainActivity;

    // View lookup cache
    private static class ViewHolder {
        TextView odbiorca;
        TextView kwota;
        TextView dataPlatnosci;
        TextView uwagi;
    }

    public CustomAdapter(ArrayList<Faktura> data, Context context, Activity mainActivity) {
        super(context, R.layout.wiersz, data);
        this.faktury = data;
        this.mContext=context;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Faktura dataModel = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.wiersz, parent, false);
            viewHolder.odbiorca = (TextView) convertView.findViewById(R.id.odbiorca_wartosc);
            viewHolder.kwota = (TextView) convertView.findViewById(R.id.kwota_wartosc);
            viewHolder.dataPlatnosci = (TextView) convertView.findViewById(R.id.data_wartosc);
            viewHolder.uwagi = (TextView) convertView.findViewById(R.id.uwagi_wartosc);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        viewHolder.odbiorca.setText(dataModel.getOdbiorca());
        viewHolder.kwota.setText(dataModel.getKwota().toString() + " PLN");
        viewHolder.dataPlatnosci.setText(dataModel.getTerminString());
        viewHolder.uwagi.setText(dataModel.getUwagi());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cache.getInstance().setZaznaczonaFaktura(dataModel);
                mainActivity.registerForContextMenu(v);
                mainActivity.openContextMenu(v);
                mainActivity.unregisterForContextMenu(v);
            }
        });

        return convertView;
    }


}