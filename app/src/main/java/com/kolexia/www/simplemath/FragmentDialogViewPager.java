package com.kolexia.www.simplemath;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by mike on 11/27/15.
 */
public class FragmentDialogViewPager extends DialogFragment {
    public static final String KEY = "sendObject";
    private static final String PECAHAN = "pecahan";
    private static final int REQUEST_PECAHAN = 0;
    public ArrayList<String> res_hitung = new ArrayList<String>();
    public ArrayList<String> res_pecahan = new ArrayList<String>();
    public static int state = 0;
    public String result="", waktu="";
    public int position = 0;
    Pecahan p;
    public static FragmentDialogViewPager newInstance(UUID id,int position,int state) {
        Bundle args = new Bundle();
        args.putSerializable(KEY, id);
        args.putInt("position", position);
        args.putInt("state",state);
        FragmentDialogViewPager fragment = new FragmentDialogViewPager();
        fragment.setArguments(args);
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID id = (UUID)getArguments().getSerializable(KEY);
        position = getArguments().getInt("position");
        state = getArguments().getInt("state");
        //Toast.makeText(getActivity(), id.toString(), Toast.LENGTH_LONG).show();
        p = SingletonAngka.get(getActivity()).getAngka(id);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_dialog_viewpager, parent, false);
        RadioGroup radio_g = (RadioGroup)v.findViewById(R.id.rg_operate);
        final TextView txt_result = (TextView)v.findViewById(R.id.txt_result);
        radio_g.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radio_b = (RadioButton)v.findViewById(i);
                if(radio_b.getText().toString().equals("Plus")){
                    p.initPlus();
                    res_hitung = p.getCaraHitung();
                    res_pecahan = p.getCaraBagi();
                    result = p.getResult();
                    state = 1;
                } else if(radio_b.getText().toString().equals("Minus")){

                    p.initMinus();
                    res_hitung = p.getCaraHitung();
                    res_pecahan = p.getCaraBagi();
                    result = p.getResult();
                    state = 2;
                } else if(radio_b.getText().toString().equals("Times")){

                    p.initTimes();
                    res_hitung = p.getCaraHitung();
                    res_pecahan = p.getCaraBagi();
                    result = p.getResult();
                    state = 3;
                } else if(radio_b.getText().toString().equals("Divide")){
                    p.initDivide();
                    res_hitung = p.getCaraHitung();
                    res_pecahan = p.getCaraBagi();
                    result = p.getResult();
                    state = 4;
                }
                txt_result.setText(result);
            }
        });
        Button buttonOK = (Button)v.findViewById(R.id.btn_submit);
        Button buttonCancel = (Button)v.findViewById(R.id.btn_cancel);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if( mDialogResult != null ){
//                    mDialogResult.finish(p,state);
//                }
                state = (state * 10) + position;
                getActivity().setResult(state);
                getActivity().finish();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().setResult(0);
                getActivity().finish();
            }
        });
        ((RadioButton)radio_g.getChildAt(state)).setChecked(true);
        txt_result.setText(p.getResult());
        return v;
    }

}
