package com.kolexia.www.simplemath;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mike on 11/27/15.
 */
public class FragmentDialog extends Dialog {
    public static ArrayList<Integer> number = new ArrayList<Integer>();
    public static RadioGroup radio_g;
    public static RadioButton radio_b;
    public static int state=1;
    public ArrayList<String> res_hitung = new ArrayList<String>();
    public ArrayList<String> res_pecahan = new ArrayList<String>();
    public TextView txt_result;
    public String result="", waktu="";
    OnMyDialogResult mDialogResult;
    public void setDialogResult(OnMyDialogResult dialogResult){
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult{
        void finish(Pecahan result, int i);
    }
    public FragmentDialog(Context context,ArrayList<Integer> p){
        super(context);
        number = p;
    }
    @Override
     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dialog);
        radio_g = (RadioGroup)findViewById(R.id.rg_operate);
        txt_result = (TextView)findViewById(R.id.txt_result);
        radio_g.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radio_b = (RadioButton)findViewById(i);
                if(radio_b.getText().toString().equals("Plus")){
                    Pecahan p = new Pecahan(number.get(0),number.get(1),number.get(2),number.get(3));
                    p.initPlus();
                    res_hitung = p.getCaraHitung();
                    res_pecahan = p.getCaraBagi();
                    result = p.getResult();
                    state = 1;
                } else if(radio_b.getText().toString().equals("Minus")){
                    Pecahan p = new Pecahan(number.get(0),number.get(1),number.get(2),number.get(3));
                    p.initMinus();
                    res_hitung = p.getCaraHitung();
                    res_pecahan = p.getCaraBagi();
                    result = p.getResult();
                    state = 2;
                } else if(radio_b.getText().toString().equals("Times")){
                    Pecahan p = new Pecahan(number.get(0),number.get(1),number.get(2),number.get(3));
                    p.initTimes();
                    res_hitung = p.getCaraHitung();
                    res_pecahan = p.getCaraBagi();
                    result = p.getResult();
                    state = 3;
                } else if(radio_b.getText().toString().equals("Divide")){
                    Pecahan p = new Pecahan(number.get(0),number.get(1),number.get(2),number.get(3));
                    p.initDivide();
                    res_hitung = p.getCaraHitung();
                    res_pecahan = p.getCaraBagi();
                    result = p.getResult();
                    state = 4;
                }
                txt_result.setText(result);
            }
        });
        final Pecahan p = new Pecahan(number.get(0),number.get(1),number.get(2),number.get(3));
        final ArrayList<Integer> pp = new ArrayList<Integer>();
        pp.add(number.get(0));
        pp.add(number.get(1));
        pp.add(number.get(2));
        pp.add(number.get(3));
        p.initPlus();
        res_hitung = p.getCaraHitung();
        res_pecahan = p.getCaraBagi();
        result = p.getResult();
        txt_result.setText(result);
        Button buttonOK = (Button) findViewById(R.id.btn_submit);
        Button buttonCancel = (Button) findViewById(R.id.btn_cancel);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( mDialogResult != null ){
                    mDialogResult.finish(p,state);
                }
                FragmentDialog.this.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentDialog.this.dismiss();
            }
        });
    }
}
