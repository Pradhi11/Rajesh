package com.example.pradeep.rajtrack.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PRADEEP on 12/16/2015.
 */
public class FeePayAdapter extends BaseAdapter{

    Context context;
    ArrayList<HashMap<String,String>> feePayList;
    @Override
    public int getCount() {
        return feePayList.size();
    }

    @Override
    public Object getItem(int position) {
        return feePayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
