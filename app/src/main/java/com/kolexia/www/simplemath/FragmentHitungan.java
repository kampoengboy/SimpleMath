package com.kolexia.www.simplemath;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentHitungan extends Fragment {

    public FragmentHitungan() {
    }
    public int gcd(int a,int b){ return b==0? a: gcd(b,a%b); }

    public int lcm(int a,int b){
        return (a*b)/gcd(a,b);
    }
    public static View v_tmp;
    public boolean flag = true;
    public int penyebut1,penyebut2,pembilang1,pembilang2;
    public static EditText txt_penyebut1, txt_penyebut2, txt_pembilang1, txt_pembilang2;
    public Button btn_plus, btn_minus, btn_times, btn_divide, btn_waktu,btn_video;
    private static TextView txtview_result;

    private int[] ans = new int[5];

    public boolean state_has_generate = false;
    public boolean state_has_click_btn = false;

    public ArrayList<String> res_hitung = new ArrayList<String>();
    public ArrayList<String> res_pecahan = new ArrayList<String>();

    public String result="", waktu="";

    private static final String KEY = "index";
    private static final String KEY2 = "index2";
    private static final String KEY3 = "index3";
    private static final String KEY4 = "index4";
    private static final String KEY5 = "index5";
    private static final String KEY6 = "index6";

    public void add_to_btntime(View v){
        btn_waktu = (Button)v.findViewById(R.id.btn_waktu);
        Date datenow = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        waktu = "Solved at "+DATE_FORMAT.format(datenow);
        btn_waktu.setText(waktu);
    }
    public void insert_function_to_btn(final View v){
        Button btn_carahitung = (Button)v.findViewById(R.id.btn_carahitung);
        Button btn_carabagi = (Button)v.findViewById(R.id.btn_carabagi);
        Button btn_clear = (Button)v.findViewById(R.id.btn_clear);
        Button btn_video = (Button)v.findViewById(R.id.btn_video);
        btn_carahitung.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckBox chk_carahitung = (CheckBox)v.findViewById(R.id.chk_carahitung);
                        state_has_generate = true;
                        txt_penyebut1 = (EditText)v.findViewById(R.id.txt_penyebut1);
                        txt_penyebut2 = (EditText)v.findViewById(R.id.txt_penyebut2);
                        txt_pembilang1 = (EditText)v.findViewById(R.id.txt_pembilang1);
                        txt_pembilang2 = (EditText)v.findViewById(R.id.txt_pembilang2);
                        chk_carahitung.setChecked(true);
                        change_state_btn_clear(true);
                        txt_pembilang1.setEnabled(false);
                        txt_penyebut1.setEnabled(false);
                        txt_penyebut2.setEnabled(false);
                        txt_pembilang2.setEnabled(false);
                        Intent intent = new Intent (getActivity(),ActivityCara.class);
                        intent.putStringArrayListExtra("session_id",res_hitung);
                        startActivity(intent);
                    }
                }
        );
        btn_carabagi.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckBox chk_carabagi = (CheckBox)v.findViewById(R.id.chk_carabagi);
                        txt_penyebut1 = (EditText)v.findViewById(R.id.txt_penyebut1);
                        txt_penyebut2 = (EditText)v.findViewById(R.id.txt_penyebut2);
                        txt_pembilang1 = (EditText)v.findViewById(R.id.txt_pembilang1);
                        txt_pembilang2 = (EditText)v.findViewById(R.id.txt_pembilang2);
                        chk_carabagi.setChecked(true);
                        state_has_generate = true;
                        change_state_btn_clear(true);
                        txt_pembilang1.setEnabled(false);
                        txt_penyebut1.setEnabled(false);
                        txt_penyebut2.setEnabled(false);
                        txt_pembilang2.setEnabled(false);
                        Intent intent = new Intent (getActivity(),ActivityCara.class);
                        intent.putStringArrayListExtra("session_id",res_pecahan);
                        startActivity(intent);
                    }
                }
        );
        btn_clear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckBox chk_carabagi = (CheckBox) v.findViewById(R.id.chk_carabagi);
                        CheckBox chk_carahitung = (CheckBox) v.findViewById(R.id.chk_carahitung);
                        res_hitung.clear();
                        res_pecahan.clear();
                        result = "";
                        state_has_generate = false;
                        state_has_click_btn = false;
                        chk_carabagi.setChecked(false);
                        chk_carahitung.setChecked(false);
                        change_state_btn_clear(false);
                        change_state_btnhitung_btnpecahan(false);
                    }
                }
        );
        btn_video.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogVideo dialog = new DialogVideo(getActivity());
                        dialog.setTitle("Video Tutorial");
                        dialog.show();
                    }
                }
        );
    }
    public void change_state_btnhitung_btnpecahan(boolean state)
    {
        Button btn_carahitung = (Button)v_tmp.findViewById(R.id.btn_carahitung);
        Button btn_carabagi = (Button)v_tmp.findViewById(R.id.btn_carabagi);
        btn_carabagi.setEnabled(state);
        btn_carahitung.setEnabled(state);
    }
    public void change_state_btn_clear(boolean state)
    {
        Button btn_clear = (Button)v_tmp.findViewById(R.id.btn_clear);
        btn_clear.setEnabled(state);
        if(!state) {
            txt_pembilang1.setText("");
            txt_penyebut1.setText("");
            txt_pembilang2.setText("");
            txt_penyebut2.setText("");
            txtview_result.setText("");
            txt_pembilang1.requestFocus();
            txt_pembilang1.setEnabled(true);
            txt_penyebut1.setEnabled(true);
            txt_penyebut2.setEnabled(true);
            txt_pembilang2.setEnabled(true);
        }
    }

    public void insert_number(View v){
        txt_penyebut1 = (EditText)v.findViewById(R.id.txt_penyebut1);
        txt_penyebut2 = (EditText)v.findViewById(R.id.txt_penyebut2);
        txt_pembilang1 = (EditText)v.findViewById(R.id.txt_pembilang1);
        txt_pembilang2 = (EditText)v.findViewById(R.id.txt_pembilang2);
        if(txt_pembilang1.getText().toString().length()<=0 || txt_pembilang2.getText().toString().length()<=0 || txt_penyebut1.getText().toString().length()<=0 || txt_penyebut2.getText().toString().length()<=0)
        {
            Toast.makeText(getActivity(), "Harap diisi semuanya", Toast.LENGTH_LONG).show();
            flag = false;
            return;
        }
        Pecahan pecahan = new Pecahan(Integer.parseInt(txt_pembilang1.getText().toString()),Integer.parseInt(txt_penyebut1.getText().toString()),Integer.parseInt(txt_pembilang2.getText().toString()),Integer.parseInt(txt_penyebut2.getText().toString()));
        penyebut1 = pecahan.getPenyebut1();
        pembilang1 = pecahan.getPembilang1();
        penyebut2 = pecahan.getPenyebut2();
        pembilang2 = pecahan.getPembilang2();
        if(penyebut1==0 || penyebut2==0)
        {
            Toast.makeText(getActivity(), "Penyebut tidak boleh diisi dengan angka 0", Toast.LENGTH_LONG).show();
            flag = false;
            return;
        }
    }
    public void click_plus(final View v){
        btn_plus = (Button)v.findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        insert_number(v);
                        if(!flag) {
                            flag = true;
                            return;
                        }
                        ArrayList<Integer> num = new ArrayList<Integer>();
                        num.add(pembilang1);
                        num.add(penyebut1);
                        num.add(pembilang2);
                        num.add(penyebut2);
                        FragmentDialog dialog = new FragmentDialog(getActivity(),num);
                        dialog.setTitle("Calculate Mathematical Operation");
                        dialog.setDialogResult(new FragmentDialog.OnMyDialogResult() {
                            public void finish(Pecahan p, int state) {
                                Pecahan pec = p;
                                if(state==1){
                                    pec.initPlus();
                                    res_hitung = pec.getCaraHitung();
                                    res_pecahan = pec.getCaraBagi();
                                    result = pec.getResult();
                                }else if(state==2){
                                    pec.initMinus();
                                    res_hitung = pec.getCaraHitung();
                                    res_pecahan = pec.getCaraBagi();
                                    result = pec.getResult();
                                } else if(state==3){
                                    pec.initTimes();
                                    res_hitung = pec.getCaraHitung();
                                    res_pecahan = pec.getCaraBagi();
                                    result = pec.getResult();
                                } else if(state==4){
                                    pec.initDivide();
                                    res_hitung = pec.getCaraHitung();
                                    res_pecahan = pec.getCaraBagi();
                                    result = pec.getResult();
                                }
                                txtview_result = (TextView)v.findViewById(R.id.txtview_result);
                                txtview_result.setText(result);
                                add_to_btntime(v);
                                change_state_btnhitung_btnpecahan(true);
                            }
                        });
                        dialog.show();
                    }
                }
        );
    }
    /*public void click_minus(final View v){
        btn_minus = (Button)v.findViewById(R.id.btn_minus);
        btn_minus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        insert_number(v);
                        if (!flag) {
                            flag = true;
                            return;
                        }
                        add_to_btntime(v);
                        state_has_click_btn = true;
                        res_hitung.clear();
                        res_pecahan.clear();
                        Pecahan p = new Pecahan(pembilang1,penyebut1,pembilang2,penyebut2);
                        p.initMinus();
                        res_hitung = p.getCaraHitung();
                        res_pecahan = p.getCaraBagi();
                        result = p.getResult();
                        txtview_result = (TextView)v.findViewById(R.id.txtview_result);
                        txtview_result.setText(result);
                        change_state_btnhitung_btnpecahan(true);
                    }
                }
        );
    }
    public void click_times(final View v){
        btn_times = (Button)v.findViewById(R.id.btn_times);
        btn_times.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        insert_number(v);
                        if (!flag) {
                            flag = true;
                            return;
                        }
                        add_to_btntime(v);
                        state_has_click_btn = true;
                        res_hitung.clear();
                        res_pecahan.clear();
                        Pecahan p = new Pecahan(pembilang1,penyebut1,pembilang2,penyebut2);
                        p.initTimes();
                        res_hitung = p.getCaraHitung();
                        res_pecahan = p.getCaraBagi();
                        result = p.getResult();
                        txtview_result = (TextView)v.findViewById(R.id.txtview_result);
                        txtview_result.setText(result);
                        change_state_btnhitung_btnpecahan(true);
                    }
                }
        );
    }
    public void click_divide(final View v){
        btn_divide = (Button)v.findViewById(R.id.btn_divide);
        btn_divide.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        insert_number(v);
                        if (!flag) {
                            flag = true;
                            return;
                        }
                        add_to_btntime(v);
                        state_has_click_btn = true;
                        res_hitung.clear();
                        res_pecahan.clear();
                        Pecahan p = new Pecahan(pembilang1,penyebut1,pembilang2,penyebut2);
                        p.initDivide();
                        res_hitung = p.getCaraHitung();
                        res_pecahan = p.getCaraBagi();
                        result = p.getResult();
                        txtview_result = (TextView)v.findViewById(R.id.txtview_result);
                        txtview_result.setText(result);
                        change_state_btnhitung_btnpecahan(true);
                    }
                }
        );
    }*/
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY,result);
        savedInstanceState.putBoolean(KEY2,state_has_generate);
        savedInstanceState.putStringArrayList(KEY3,res_hitung);
        savedInstanceState.putStringArrayList(KEY4,res_pecahan);
        savedInstanceState.putBoolean(KEY5,state_has_click_btn);
        savedInstanceState.putString(KEY6, waktu);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_hitungan, container, false);

        v_tmp = v;
        if (savedInstanceState != null){
            result = savedInstanceState.getString(KEY);
            state_has_generate = savedInstanceState.getBoolean(KEY2);
            res_hitung = savedInstanceState.getStringArrayList(KEY3);
            res_pecahan = savedInstanceState.getStringArrayList(KEY4);
            state_has_click_btn = savedInstanceState.getBoolean(KEY5);
            waktu = savedInstanceState.getString(KEY6);
        }
        change_state_btnhitung_btnpecahan(state_has_click_btn);
        if(state_has_generate)
        {
            change_state_btn_clear(true);
            txt_penyebut1 = (EditText)v.findViewById(R.id.txt_penyebut1);
            txt_penyebut2 = (EditText)v.findViewById(R.id.txt_penyebut2);
            txt_pembilang1 = (EditText)v.findViewById(R.id.txt_pembilang1);
            txt_pembilang2 = (EditText)v.findViewById(R.id.txt_pembilang2);
            txt_pembilang1.setEnabled(false);
            txt_penyebut1.setEnabled(false);
            txt_penyebut2.setEnabled(false);
            txt_pembilang2.setEnabled(false);
        }
        txtview_result = (TextView)v.findViewById(R.id.txtview_result);
        txtview_result.setText(result);
        btn_waktu = (Button)v.findViewById(R.id.btn_waktu);
        btn_waktu.setText(waktu);
        click_plus(v);
        //click_minus(v);
        //click_times(v);
        //click_divide(v);
        insert_function_to_btn(v);
        return v;
    }
}
