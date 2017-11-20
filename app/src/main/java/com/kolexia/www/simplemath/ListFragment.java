package com.kolexia.www.simplemath;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListFragment extends Fragment {

    public ListFragment() {
    }
    public int gcd(int a,int b){ return b==0? a: gcd(b,a%b); }
    public int lcm(int a,int b){
        return (a*b)/gcd(a,b);
    }

    private static ListView list_view;

    public static ArrayList<Integer> tmp = new ArrayList<Integer>();
    public static ArrayList<Integer> tmp_num = new ArrayList<Integer>();
    public static ArrayList<ArrayList<Integer>> num = new ArrayList<ArrayList<Integer>>();
    private static String[] res = new String[] {"Tom","Mark","John","Patrick","Add calculation"};
    private static String[] tmp_res = new String[] {"Tom","Mark","John","Patrick","Add calculation"};
    public static int idx_res = 0;
    public static CheckBox chk_list1, chk_list2,chk_list3,chk_list4;
    public ArrayList<String> res_hitung = new ArrayList<String>();
    public ArrayList<String> res_pecahan = new ArrayList<String>();
    public ArrayList<Pecahan> numList = new ArrayList<Pecahan>();

    public int penyebut1,pembilang1,penyebut2,pembilang2;


    public ArrayList<ArrayList<String>> res_cara = new ArrayList<ArrayList<String>>();

    public String result="";
    private static final String KEY = "index";
    private static final String KEY1 = "index1";
    private static final String KEY2 = "index2";
    private static final String KEY3 = "index3";
    private static final String KEY4 = "index4";
    private static final String KEY5 = "index5";
    private static final String KEY6 = "index6";
    private static final String KEY7 = "index7";
    public void generate_plus(int state){
        switch (state){
            case 1: {
                pembilang1 = numList.get(0).getPembilang1();
                penyebut1 = numList.get(0).getPenyebut1();
                pembilang2 = numList.get(0).getPembilang2();
                penyebut2 = numList.get(0).getPenyebut2();
                tmp_num.clear();
                tmp_num.add(pembilang1);
                tmp_num.add(penyebut1);
                tmp_num.add(pembilang2);
                tmp_num.add(penyebut2);
                num.add(tmp_num);
                Pecahan p = numList.get(0);
                p.initPlus();
                res_hitung = p.getCaraHitung();
                res_pecahan = p.getCaraBagi();
                result = p.getResult();
                res_cara.add(res_hitung);
                res[idx_res] = result;
                tmp_res[idx_res] = result;
                idx_res++;
                break;
            }
            case 2: {
                pembilang1 = numList.get(1).getPembilang1();
                penyebut1 = numList.get(1).getPenyebut1();
                pembilang2 = numList.get(1).getPembilang2();
                penyebut2 = numList.get(1).getPenyebut2();
                tmp_num.clear();
                tmp_num.add(pembilang1);
                tmp_num.add(penyebut1);
                tmp_num.add(pembilang2);
                tmp_num.add(penyebut2);
                num.add(tmp_num);
                Pecahan p = numList.get(1);
                p.initMinus();
                res_hitung = p.getCaraHitung();
                res_pecahan = p.getCaraBagi();
                result = p.getResult();
                res_cara.add(res_hitung);
                res[idx_res] = result;
                tmp_res[idx_res] = result;
                idx_res++;
                break;
            }
            case 3: {
                pembilang1 = numList.get(2).getPembilang1();
                penyebut1 = numList.get(2).getPenyebut1();
                pembilang2 = numList.get(2).getPembilang2();
                penyebut2 = numList.get(2).getPenyebut2();
                tmp_num.clear();
                tmp_num.add(pembilang1);
                tmp_num.add(penyebut1);
                tmp_num.add(pembilang2);
                tmp_num.add(penyebut2);
                num.add(tmp_num);
                Pecahan p = numList.get(2);
                p.initTimes();
                res_hitung = p.getCaraHitung();
                res_pecahan = p.getCaraBagi();
                result = p.getResult();
                res_cara.add(res_hitung);
                res[idx_res] = result;
                tmp_res[idx_res] = result;
                idx_res++;
                break;
            }
            case 4 : {
                pembilang1 = numList.get(3).getPembilang1();
                penyebut1 = numList.get(3).getPenyebut1();
                pembilang2 = numList.get(3).getPembilang2();
                penyebut2 = numList.get(3).getPenyebut2();
                tmp_num.clear();
                tmp_num.add(pembilang1);
                tmp_num.add(penyebut1);
                tmp_num.add(pembilang2);
                tmp_num.add(penyebut2);
                num.add(tmp_num);
                Pecahan p = numList.get(3);
                p.initDivide();
                res_hitung = p.getCaraHitung();
                res_pecahan = p.getCaraBagi();
                result = p.getResult();
                res_cara.add(res_hitung);
                res[idx_res] = result;
                tmp_res[idx_res] = result;
                break;
            }
        }
    }
    public void generate_random_num(){
        for(int i=1;i<=4;i++)
        {
            generate_plus(i);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        numList = SingletonAngka.get(getActivity()).getAllAngka();
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_fragment, container, false);


        if (savedInstanceState != null){
            res[0] = savedInstanceState.getString(KEY);
            res[1] = savedInstanceState.getString(KEY1);
            res[2] = savedInstanceState.getString(KEY2);
            res[3] = savedInstanceState.getString(KEY3);
            res_cara.add(savedInstanceState.getStringArrayList(KEY4));
            res_cara.add(savedInstanceState.getStringArrayList(KEY5));
            res_cara.add(savedInstanceState.getStringArrayList(KEY6));
            res_cara.add(savedInstanceState.getStringArrayList(KEY7));
        }
        else {
            generate_random_num();
        }
        list_view = (ListView)v.findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,res);
        chk_list1 = (CheckBox)v.findViewById(R.id.chk_list1);
        chk_list2 = (CheckBox)v.findViewById(R.id.chk_list2);
        chk_list3 = (CheckBox)v.findViewById(R.id.chk_list3);
        chk_list4 = (CheckBox)v.findViewById(R.id.chk_list4);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i == 4) {
                            Intent intent = new Intent(getActivity(), ActivityHitungan.class);
                            startActivity(intent);
                        } else {
                            Pecahan p = numList.get(i);
//                              FragmentManager fragmentManager = getFragmentManager();
//                              DialogViewPager dialog = new DialogViewPager(getActivity(),numList);
//                              dialog.show(fragmentManager,"dialogFragment");
                            Intent intent = new Intent(getActivity(), DialogViewPager.class);
                            intent.putExtra(FragmentDialogViewPager.KEY, p.getId());
                            startActivityForResult(intent, 0);
//                            FragmentManager fragmentManager = getFragmentManager();
//                            ActivityViewPager dialog = new ActivityViewPager(getActivity(),num);
//                            dialog.show(fragmentManager,"dialogFragment");
//                            FragmentDialog dialog = new FragmentDialog(getActivity(),tmp_num);
//                            dialog.setTitle("Calculate Mathematical Operation");
//                            dialog.setDialogResult(new FragmentDialog.OnMyDialogResult() {
//                                public void finish(Pecahan p, int state) {
//                                    Pecahan pec = p;
//                                    if(state==1){
//                                        pec.initPlus();
//                                        res_hitung = pec.getCaraHitung();
//                                        res_pecahan = pec.getCaraBagi();
//                                        result = pec.getResult();
//                                    }else if(state==2){
//                                        pec.initMinus();
//                                        res_hitung = pec.getCaraHitung();
//                                        res_pecahan = pec.getCaraBagi();
//                                        result = pec.getResult();
//                                    } else if(state==3){
//                                        pec.initTimes();
//                                        res_hitung = pec.getCaraHitung();
//                                        res_pecahan = pec.getCaraBagi();
//                                        result = pec.getResult();
//                                    } else if(state==4){
//                                        pec.initDivide();
//                                        res_hitung = pec.getCaraHitung();
//                                        res_pecahan = pec.getCaraBagi();
//                                        result = pec.getResult();
//                                    }
//                                }
//                            });
//                            dialog.show();
//                            final PagerAdapter adapter = new PagerAdapter(getFragmentManager());
//                            final ViewPager myPager = (ViewPager)dialog.findViewById(R.id.viewpagerdialog);
//                            myPager.setAdapter(adapter);
//                            myPager.setCurrentItem(2);
//////                            ActivityViewPager dialog = new ActivityViewPager();
//////                            dialog.show(getChildFragmentManager(), "item_description_dialog");
                            //Intent intent = new Intent(getActivity(), ActivityViewPager.class);
                            //intent.putStringArrayListExtra("session_id", res_cara.get(i));
                            //startActivity(intent);
                        }
                    }
                }
        );
        return v;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("testing",Integer.toString(resultCode));
        if (resultCode > 0) {
            int state = resultCode / 10;
            int position = resultCode % 10;
            Log.i("position",Integer.toString(position));
            if(position==0){
                chk_list1.setChecked(true);
            } else if(position==1){
                chk_list2.setChecked(true);
            } else if(position==2){
                chk_list3.setChecked(true);
            } else if(position==3){
                chk_list4.setChecked(true);
            }

            if(state==1){
                tmp_res[position] = res[0];
            } else if(state==2){
                tmp_res[position] = res[1];
            } else if(state==3){
                tmp_res[position] = res[2];
            } else if(state==4){
                tmp_res[position] = res[3];
            }
            list_view.setAdapter(null);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,tmp_res);
            list_view.setAdapter(adapter);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY, res[0]);
        savedInstanceState.putString(KEY1,res[1]);
        savedInstanceState.putString(KEY2,res[2]);
        savedInstanceState.putString(KEY3, res[3]);
        savedInstanceState.putStringArrayList(KEY4, res_cara.get(0));
        savedInstanceState.putStringArrayList(KEY5, res_cara.get(1));
        savedInstanceState.putStringArrayList(KEY6, res_cara.get(2));
        savedInstanceState.putStringArrayList(KEY7, res_cara.get(3));
    }
}
