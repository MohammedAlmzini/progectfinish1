package com.ahmmedalmzini783.progectfinish.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.R;

import java.util.ArrayList;

public class CustomAdapterMonth extends BaseAdapter {
    Context context;
    ArrayList<String> data;

    public CustomAdapterMonth(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_month, viewGroup, false);
        }

        TextView month = view.findViewById(R.id.tv_month);
        month.setText(data.get(i));

        return view;
    }
}
