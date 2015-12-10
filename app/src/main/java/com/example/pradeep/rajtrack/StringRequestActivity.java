package com.example.pradeep.rajtrack;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import com.example.pradeep.rajtrack.AppController;
import com.example.pradeep.rajtrack.utils.Const;
import com.example.pradeep.rajtrack.utils.Parser;
import com.example.pradeep.rajtrack.utils.ResultAdapter;
import com.example.pradeep.rajtrack.utils.TimeTableAdapter;
import com.example.pradeep.rajtrack.utils.UseFullMethods;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StringRequestActivity extends Activity implements AdapterView.OnItemClickListener{

	private String TAG = StringRequestActivity.class.getSimpleName();
	//private Button btnStringReq;
	private TextView msgResponse;
	private ProgressDialog pDialog;

	// This tag will be used to cancel the request
	private String tag_string_req = "string_req";
	private String tag_json_obj = "jobj_req";
	TimeTableAdapter timeTableAdapter;
	ListView listView;
	String usn;
	UseFullMethods instance;
	ArrayList<HashMap<String,String>> results;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_string);

		usn=getIntent().getStringExtra("usn");
		Log.d("=====", usn);
		instance=new UseFullMethods();
		listView= (ListView) findViewById(R.id.populate);
		//btnStringReq = (Button) findViewById(R.id.btnStringReq);
		msgResponse = (TextView) findViewById(R.id.msgResponse);

		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);

		/*btnStringReq.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				makeStringReq();
			}
		});*/
		makeJsonObjReq();
		listView.setOnItemClickListener(this);
	}

	private void showProgressDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hideProgressDialog() {
		if (pDialog.isShowing())
			pDialog.hide();
	}

	/**
	 * Making json object request
	 * */
	private void makeStringReq() {
		showProgressDialog();

	}

	private void makeJsonObjReq() {
		showProgressDialog();
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,instance.conCat(usn,Const.URL_TIME_TABLE), null,new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.d(TAG, response.toString());


				Parser parse=new Parser(StringRequestActivity.this,response);
				results=parse.getTimeTable();

				if(results!=null)
				{
					timeTableAdapter=new TimeTableAdapter(StringRequestActivity.this,results);
					listView.setAdapter(timeTableAdapter);
				}
				else
				{
					Toast.makeText(StringRequestActivity.this, "result null guru", Toast.LENGTH_LONG).show();
				}

				msgResponse.setText(response.toString());
				hideProgressDialog();
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				hideProgressDialog();
			}
		}) {

			/**
			 * Passing some request headers
			 * */
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				return headers;
			}

			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("name", "Androidhive");
				params.put("email", "abc@androidhive.info");
				params.put("pass", "password123");

				return params;
			}

		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq,tag_json_obj);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		Toast.makeText(StringRequestActivity.this,"working",Toast.LENGTH_LONG).show();
		HashMap<String,String> TimeTableObject = results.get(position);

		String lSub=TimeTableObject.get("lsub");
		String lExamDate=TimeTableObject.get("lexamDate");
		String lExamTime=TimeTableObject.get("lexamTime");

		String lSubCode=TimeTableObject.get("lsubCode");
		String lBranch=TimeTableObject.get("lBranch");
		String lSem=TimeTableObject.get("lSem");

		Intent displayIntent=new Intent(StringRequestActivity.this,DisplayTimeTableActivity.class);
		displayIntent.putExtra("lSub",lSub);
		displayIntent.putExtra("lExamDate",lExamDate);
		displayIntent.putExtra("lExamTime",lExamTime);
		displayIntent.putExtra("lSubCode",lSubCode);
		displayIntent.putExtra("lBranch",lBranch);
		displayIntent.putExtra("lSem",lSem);
		startActivity(displayIntent);
	}
}