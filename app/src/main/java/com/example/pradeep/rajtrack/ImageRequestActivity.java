package com.example.pradeep.rajtrack;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.example.pradeep.rajtrack.utils.Parser;
import com.example.pradeep.rajtrack.AppController;
import com.example.pradeep.rajtrack.utils.Const;
import com.example.pradeep.rajtrack.utils.ResultAdapter;
import com.example.pradeep.rajtrack.utils.UseFullMethods;

import org.json.JSONObject;

public class ImageRequestActivity extends Activity implements AdapterView.OnItemClickListener {

	private static final String TAG = ImageRequestActivity.class
			.getSimpleName();
	private Button btnImageReq;
	private NetworkImageView imgNetWorkView;
	private ImageView imageView;
	private String tag_json_obj = "jobj_req";
	private ProgressDialog pDialog;
	private TextView msgResponse;
	String usn;
	UseFullMethods instance;
	ResultAdapter resultAdapter;
	ListView listView;
	ArrayList<HashMap<String,String>> results;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);

		usn=getIntent().getStringExtra("usn");
		Log.d("=====", usn);
		instance=new UseFullMethods();
        listView= (ListView) findViewById(R.id.populate);
		btnImageReq = (Button) findViewById(R.id.btnImageReq);
		imgNetWorkView = (NetworkImageView) findViewById(R.id.imgNetwork);
		imageView = (ImageView) findViewById(R.id.imgView);
		msgResponse = (TextView) findViewById(R.id.msgResponse);
		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);

		/*btnImageReq.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				makeImageRequest();
			}
		});*/
		listView.setOnItemClickListener(this);
		makeJsonObjReq();
	}

	private void makeImageRequest() {
		ImageLoader imageLoader = AppController.getInstance().getImageLoader();

		// If you are using NetworkImageView
		imgNetWorkView.setImageUrl(Const.URL_IMAGE, imageLoader);

		
		// If you are using normal ImageView
		/*imageLoader.get(Const.URL_IMAGE, new ImageListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, "Image Load Error: " + error.getMessage());
			}

			@Override
			public void onResponse(ImageContainer response, boolean arg1) {
				if (response.getBitmap() != null) {
					// load image into imageview
					imageView.setImageBitmap(response.getBitmap());
				}
			}
		});*/

		// Loading image with placeholder and error image
		imageLoader.get(Const.URL_IMAGE, ImageLoader.getImageListener(
				imageView, R.drawable.ico_loading, R.drawable.ico_error));
		
		Cache cache = AppController.getInstance().getRequestQueue().getCache();
		Entry entry = cache.get(Const.URL_IMAGE);
		if(entry != null){
			try {
				String data = new String(entry.data, "UTF-8");
				// handle data, like converting it to xml, json, bitmap etc.,
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			// cached response doesn't exists. Make a network call here
		}

	}
	private void makeJsonObjReq() {
		showProgressDialog();
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,instance.conCat(usn,Const.URL_RESULT), null,new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.d(TAG, response.toString());
				Parser parse=new Parser(ImageRequestActivity.this,response);
				 results=parse.getResult();

				if(results!=null)
				{
					resultAdapter=new ResultAdapter(ImageRequestActivity.this,results);
					listView.setAdapter(resultAdapter);
				}
				else
				{
					Toast.makeText(ImageRequestActivity.this,"result null guru",Toast.LENGTH_LONG).show();
				}

				msgResponse.setText(parse.getResult().toString());
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
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
	}

	private void showProgressDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hideProgressDialog() {
		if (pDialog.isShowing())
			pDialog.hide();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		HashMap<String,String> resultObject = results.get(position);

		String s=resultObject.get("NAME");
		String s1=resultObject.get("EXTERNAL");
		String s2=resultObject.get("TOTAL");

		Log.d("##",s+" : "+s1+": "+": "+s2);


	}
}
