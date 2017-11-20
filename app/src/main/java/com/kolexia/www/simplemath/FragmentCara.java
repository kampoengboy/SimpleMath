package com.kolexia.www.simplemath;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentCara extends Fragment {
    public ArrayList<String> res;
    public FragmentCara() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cara, container, false);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            res = extras.getStringArrayList("session_id");
        }else{
            res = new ArrayList<String>();
        }

        TextView x = (TextView)rootView.findViewById(R.id.txt);

        for(int i=0;i<res.size();i++) {
            x.append(res.get(i));
            x.append("\n");
        }
        return rootView;
    }
}
