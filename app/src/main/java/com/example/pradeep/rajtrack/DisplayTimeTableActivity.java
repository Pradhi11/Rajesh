package com.example.pradeep.rajtrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayTimeTableActivity extends AppCompatActivity {

    TextView mTsub,mTexamDate,mTexamTime,mTsubCode,mTbranch,mTsem;
    String    mSsub,mexamDate,mSexamTime,mSsubCode,mSbranch,mSsem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_time_table);

        findViews();

        mSsub=getIntent().getStringExtra("lSub");
        mexamDate=getIntent().getStringExtra("lExamDate");
        mSexamTime=getIntent().getStringExtra("lExamTime");
        mSsubCode=getIntent().getStringExtra("lSubCode");
        mSbranch=getIntent().getStringExtra("lBranch");
        mSsem=getIntent().getStringExtra("lSem");
        Log.d("testData",mSsub+" : "+mexamDate+" : "+mSexamTime+" : "+mSsubCode+" : "+mSbranch+" : "+mSsem);
        setView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_time_table, menu);
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

    private void findViews()
    {
        mTsub= (TextView) findViewById(R.id.d_sub_name);
        mTexamDate=(TextView) findViewById(R.id.d_exam_date);
        mTexamTime=(TextView) findViewById(R.id.d_exam_time);
        mTsubCode=(TextView) findViewById(R.id.d_subject_code);
        mTbranch=(TextView) findViewById(R.id.d_branch);
        mTsem=(TextView) findViewById(R.id.d_sem);


    }

    public void setView()
    {
        mTsub.setText(mSsub);
        mTexamDate.setText(mexamDate);
        mTexamTime.setText(mSexamTime);
        mTsubCode.setText(mSsubCode);
        mTbranch.setText(mSbranch);
        mTsem.setText(mSsem);

    }
}
