package com.example.pradeep.rajtrack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pradeep.rajtrack.utils.Const;
import com.example.pradeep.rajtrack.utils.UseFullMethods;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText mInputLogin;
    Button mLogin;
    private ProgressDialog pDialog;
    private String TAG = LoginActivity.class.getSimpleName();
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    UseFullMethods instance;
    String mUsn;
    boolean flag=false;
    String Success="Success";
    private static final String dataObject="feesData";
    private static final String FEES_KEY="fees";
    private static final String FEE_BRANCH_KEY="branch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copy_activity_login);
        mInputLogin= (EditText) findViewById(R.id.input_login);
        mLogin= (Button) findViewById(R.id.btn_clogin);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

      // mUsn=mInputLogin.getText().toString();
        mUsn="15bwsb3024";
        instance=new UseFullMethods();


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeJsonObjReq();
               /* if(flag==true)
                { Intent intent=new Intent(LoginActivity.this,CopyTabActivity.class);
                 startActivity(intent);}
                else
                {
                    Toast.makeText(LoginActivity.this,"response null",Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    private void makeJsonObjReq() {
        showProgressDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,instance.conCat(mUsn, Const.URL_JSON_OBJECT), null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                String msg=null;
                String fee=null;
                String branch=null;
                try {
                     msg=response.getString("message");
                    JSONObject feeObject=response.getJSONObject(dataObject);
                    fee=feeObject.getString(FEES_KEY);
                    branch=feeObject.getString(FEE_BRANCH_KEY);
                    Log.d(TAG, msg+" : "+fee+" : "+branch+" : ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(Success.equalsIgnoreCase(msg))
                 {
                     Intent intent=new Intent(LoginActivity.this,CopyTabActivity.class);
                     intent.putExtra("fee",fee);
                     intent.putExtra("branch",branch);
                     intent.putExtra("usn",mUsn);
                     startActivity(intent);
                     flag=true;
                 }
                else
                {
                    Toast.makeText(LoginActivity.this,"response null",Toast.LENGTH_LONG).show();
                }
                hideProgressDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                flag=false;
                int count=0;
                makeJsonObjReq();
                Log.d("count",": "+count++);
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
}
