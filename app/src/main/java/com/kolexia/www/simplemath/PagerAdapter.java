package com.kolexia.www.simplemath;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike on 11/27/15.
 */
public class PagerAdapter extends android.support.v4.view.PagerAdapter {
    private Activity activity;
    private ArrayList<Pecahan> newFeatureList;
    private LayoutInflater layoutInflater;
    private View updatableView;
    int pos = 0;
    public PagerAdapter(Activity activity, ArrayList<Pecahan> newFeatureList,int pos) {
        this.activity = activity;
        this.newFeatureList = newFeatureList;
        this.pos = pos;
    }

    @Override
    public int getCount() {
        return newFeatureList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout)object);
    }
    @Override
    public int getItemPosition(Object object) {
        int position = 1;
        if (position >= 0) {
            // The current data matches the data in this active fragment, so let it be as it is.
            return position;
        } else {
            // Returning POSITION_NONE means the current data does not matches the data this fragment is showing right now.  Returning POSITION_NONE constant will force the fragment to redraw its view layout all over again and show new data.
            return POSITION_NONE;
        }
    }
    @Override
    public Object instantiateItem(final ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_dialog, container, false);

        final TextView txt_result = (TextView)view.findViewById(R.id.txt_result);
        Button buttonOK = (Button)view.findViewById(R.id.btn_submit);
        buttonOK.setText("Change");
        Button buttonCancel = (Button)view.findViewById(R.id.btn_cancel);
        Pecahan newFeature = newFeatureList.get(pos);
        Log.i("buat", "instantiateItem() [position: " + position + "]" + " childCount:" + container.getChildCount());
        newFeature.initPlus();
        if (newFeature != null) {
            if (txt_result != null) {
                txt_result.setText(newFeature.getResult());
            }
        }
//        buttonOK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if( mDialogResult != null ){
////                    mDialogResult.finish(p,state);
////                }
////                txt_result.setText("yoo");
//            }
//        });
//        buttonCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //FragmentDialog.this.dismiss();
//            }
//        });
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout)object);
        Log.i("destroy", "destroyItem() [position: " + position + "]" + " childCount:" + container.getChildCount());
    }
}
