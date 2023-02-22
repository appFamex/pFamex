package com.example.drawerfamex.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.drawerfamex.R;

public class ItemAdapter extends BaseAdapter {

    Context context;
    int[]  flagsIcons;


    public ItemAdapter(Context context, int[] flagsIcons) {
        this.context = context;
        this.flagsIcons = flagsIcons;
    }

    @Override
    public int getCount() {
        return flagsIcons.length;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.itemlayout,viewGroup,false);
        ImageView imageView = view.findViewById(R.id.flagIcon);
        imageView.setImageResource(flagsIcons[i]);
        return view;
    }
}
