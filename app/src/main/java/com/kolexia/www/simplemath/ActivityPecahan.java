package com.kolexia.www.simplemath;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class ActivityPecahan extends FragmentActivity {
    public ArrayList<String> res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pecahan);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            res = extras.getStringArrayList("session_id");
        }else{
            res = new ArrayList<String>();
        }
        TextView x = (TextView)findViewById(R.id.txt_pecahan);
        for(int i=0;i<res.size();i++) {
            x.append(res.get(i));
            x.append("\n");
        }
    }

//    FragmentManager manager = getSupportFragmentManager();
//    Fragment fragment = manager.findFragmentById(R.id.container_main);
//
//    if (fragment == null) {
//        fragment = new ListFragment();
//        manager.beginTransaction()
//                .add(R.id.container_main, fragment)
//                .commit();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_pecahan, menu);
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
}
