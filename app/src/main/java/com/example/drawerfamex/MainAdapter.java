package com.example.drawerfamex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MainAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] arraynumero;
    private int[] numeroimagen;

    public MainAdapter(Context c, String[] arraynumero, int[] numeroimagen){
        context =  c;
        this.numeroimagen = numeroimagen;
        this.arraynumero = arraynumero;
    }

    @Override
    public int getCount() {
        return arraynumero.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.row_item, null);
        }

        ImageView imageview = convertView.findViewById(R.id.image_view);
        imageview.setImageResource(Integer.parseInt(arraynumero[position]));

        return convertView;
    }
}
