package com.example.pradeep.rajtrack.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pradeep.rajtrack.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PRADEEP on 12/8/2015.
 */
public class TimeTableAdapter extends BaseAdapter {

    Context context;
    JSONObject json;
    LayoutInflater inflater;
    ArrayList<HashMap<String,String>> result;
    public TimeTableAdapter(Context context,ArrayList<HashMap<String,String>> result)
    {
        this.context=context;
        this.result=result;
    }
    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder;

        if(convertView==null) {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.for_row, parent, false);
            viewHolder = new ViewHolderItem();
            viewHolder.subjectView= (TextView) convertView.findViewById(R.id.subject);
            viewHolder.resultView=(TextView) convertView.findViewById(R.id.sub_code);
            HashMap<String,String> resultObject = result.get(position);
            String subJec=resultObject.get("lsub");
            String resul=resultObject.get("lsubCode");
            Log.d("==", subJec + ": " + resul);
            viewHolder.subjectView.setText(subJec);
            viewHolder.resultView.setText(resul);

            //convertView.setTag(viewHolder);


        }
        else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }
        return convertView;



    }




    static class ViewHolderItem {

        TextView subjectView;
        TextView resultView;

    }
}
