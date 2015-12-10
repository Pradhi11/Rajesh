package com.example.pradeep.rajtrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayResultDetails extends AppCompatActivity {

    TextView mTusn,mTname,mTsem,mTbranch,mTsubject,mTsubject_code,mTexternal,mTintexnal,mTtotal,mTresult;
    String mSusn,mSname,mSsem,mSbranch,mSsubject,mSsubject_code,mSexternal,mSintexnal,mStotal,mSresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result_details);

        findViews();

        mSusn=getIntent().getStringExtra("lUsn");
        mSname=getIntent().getStringExtra("lName");
        mSsem=getIntent().getStringExtra("lSem");
        mSbranch=getIntent().getStringExtra("lBranch");
        mSsubject=getIntent().getStringExtra("lSub");
        mSsubject_code=getIntent().getStringExtra("lSubjectCode");
        mSexternal=getIntent().getStringExtra("lExternal");
        mSintexnal=getIntent().getStringExtra("linternal");
        mStotal=getIntent().getStringExtra("ltotal");
        mSresult=getIntent().getStringExtra("lResult");

          setView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_result_details, menu);
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
        mTsubject= (TextView) findViewById(R.id.d_subject);
        mTusn=(TextView) findViewById(R.id.d_usn);
        mTname=(TextView) findViewById(R.id.d_name);
        mTbranch=(TextView) findViewById(R.id.d_branch);
        mTsubject_code=(TextView) findViewById(R.id.d_sub_code);
        mTexternal=(TextView) findViewById(R.id.d_external);
        mTintexnal=(TextView) findViewById(R.id.d_internal);
        mTtotal=(TextView) findViewById(R.id.d_total);
        mTresult=(TextView) findViewById(R.id.d_result);
        mTsem=(TextView) findViewById(R.id.d_sem);

    }


    public void setView()
    {
        mTsubject.setText(mSsubject);
        mTusn.setText(mSusn);
        mTname.setText(mSname);
        mTbranch.setText(mSbranch);
        mTsubject_code.setText(mSsubject_code);
        mTexternal.setText(mSexternal);
        mTintexnal.setText(mSintexnal);
        mTtotal.setText(mStotal);
        mTresult.setText(mSresult);
        mTsem.setText(mSsem);
    }
}
