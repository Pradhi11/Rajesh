package com.example.pradeep.rajtrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class VolleyMainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnJson, btnString, btnImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnString = (Button) findViewById(R.id.btnStringRequest);
        btnJson = (Button) findViewById(R.id.btnJsonRequest);
        btnImage = (Button) findViewById(R.id.btnImageRequest);

        // button click listeners
        btnString.setOnClickListener(this);
        btnJson.setOnClickListener(this);
        btnImage.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnStringRequest:
                startActivity(new Intent(VolleyMainActivity.this,StringRequestActivity.class));
                break;
            case R.id.btnJsonRequest:
                startActivity(new Intent(VolleyMainActivity.this,JsonRequestActivity.class));
                break;
            case R.id.btnImageRequest:
                startActivity(new Intent(VolleyMainActivity.this,ImageRequestActivity.class));
                break;
            default:
                break;
        }

    }
}
