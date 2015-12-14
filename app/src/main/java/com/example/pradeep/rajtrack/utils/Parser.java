package com.example.pradeep.rajtrack.utils;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PRADEEP on 12/8/2015.
 */
public class Parser {

    private static final String USN="usn";
    private static final String NAME="name";
    private static final String SEM="sem";
    private static final String BRANCH="branch";
    private static final String SUBJECT="subject";
    private static final String SUBJECT_CODE="subjectCode";
    private static final String EXTERNAL="external";
    private static final String INTERNAL="internal";
    private static final String TOTAL="total";
    private static final String RESULT="result";
    private static final String DATA_ARRAY="data";


    private static final String TSUBJECT="subject";
    private static final String TEXAM_DATE="examDate";
    private static final String TEXAM_TIME="examTime";
    private static final String TSUBJECT_CODE="subjectCode";
    private static final String TBRANCH="branch";
    private static final String TSEM="sem";



    ArrayList<HashMap<String,String>>  subJects=new ArrayList<HashMap<String,String>>();
    ArrayList<HashMap<String,String>>  timeTable=new ArrayList<HashMap<String,String>>();
    Context context;
    JSONObject jsonObject1;
    public Parser(Context context,JSONObject json)
    {
        this.context=context;
        this.jsonObject1=json;
    }


    public ArrayList<HashMap<String,String>> getResult()
    {
        JSONObject obj1=null;

        if(jsonObject1!=null)
        {

                try {
                   // JSONObject jsonObject1=new JSONObject(json);
                    String message=jsonObject1.getString("message");

                    if(!message.equalsIgnoreCase("Success"))
                    {
                        Toast.makeText(context,"call madu guru",Toast.LENGTH_LONG).show();
                    }
                    else
                    {

                        JSONArray jsonArray=jsonObject1.getJSONArray(DATA_ARRAY);
                        int ArrayLength=jsonArray.length();
                        for(int i=0;i<ArrayLength;i++)
                        {
                            JSONObject jsonObject2=jsonArray.getJSONObject(i);
                            String usn=jsonObject2.getString(USN);
                            String name=jsonObject2.getString(NAME);
                            String sem=jsonObject2.getString(SEM);
                            String branch=jsonObject2.getString(BRANCH);
                            String subject=jsonObject2.getString(SUBJECT);
                            String subjectCode=jsonObject2.getString(SUBJECT_CODE);
                            String external=jsonObject2.getString(EXTERNAL);
                            String internal=jsonObject2.getString(INTERNAL);
                            String total=jsonObject2.getString(TOTAL);
                            String result=jsonObject2.getString(RESULT);

                            HashMap<String, String> resultDetails = new HashMap<String, String>();

                            resultDetails.put("USN",usn);
                            resultDetails.put("NAME",name);
                            resultDetails.put("SEM",sem);
                            resultDetails.put("BRANCH",branch);
                            resultDetails.put("SUBJECT",subject);
                            resultDetails.put("SUBJECT_CODE",subjectCode);
                            resultDetails.put("EXTERNAL",external);
                            resultDetails.put("INTERNAL",internal);
                            resultDetails.put("TOTAL",total);
                            resultDetails.put("RESULT",result);

                            subJects.add(resultDetails);
                        }


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

        }

        return subJects;
    }




    public ArrayList<HashMap<String,String>> getTimeTable()
    {
        JSONObject obj1=null;

        if(jsonObject1!=null)
        {

            try {
                // JSONObject jsonObject1=new JSONObject(json);
                String message=jsonObject1.getString("message");

                if(!message.equalsIgnoreCase("Success"))
                {
                    Toast.makeText(context,"call madu guru",Toast.LENGTH_LONG).show();
                }
                else
                {

                    JSONArray jsonArray=jsonObject1.getJSONArray(DATA_ARRAY);
                    int ArrayLength=jsonArray.length();
                    for(int i=0;i<ArrayLength;i++)
                    {
                        JSONObject jsonObject2=jsonArray.getJSONObject(i);
                        String lsub=jsonObject2.getString(TSUBJECT);
                        String lexamDate=jsonObject2.getString(TEXAM_DATE);
                        String lexamTime=jsonObject2.getString(TEXAM_TIME);
                        String lsubCode=jsonObject2.getString(TSUBJECT_CODE);
                        String lBranch=jsonObject2.getString(TBRANCH);
                        String lSem=jsonObject2.getString(TSEM);


                        HashMap<String, String> resultDetails = new HashMap<String, String>();

                        resultDetails.put("lsub",lsub);
                        resultDetails.put("lexamDate",lexamDate);
                        resultDetails.put("lexamTime",lexamTime);
                        resultDetails.put("lsubCode",lsubCode);
                        resultDetails.put("lBranch",lBranch);
                        resultDetails.put("lSem",lSem);


                        timeTable.add(resultDetails);
                    }


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return timeTable;
    }


}
