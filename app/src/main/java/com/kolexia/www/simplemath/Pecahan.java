package com.kolexia.www.simplemath;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by mike on 9/23/15.
 */
public class Pecahan {
    private static int penyebut1,penyebut2,pembilang1,pembilang2;
    String notes,result;
    UUID id;
    private int[] ans = new int[5];
    public ArrayList<String> res_pecahan = new ArrayList<String>();
    public ArrayList<String> res_hitung = new ArrayList<String>();
    public int gcd(int a,int b){ return b==0? a: gcd(b,a%b); }
    public int lcm(int a,int b){
        return (a*b)/gcd(a,b);
    }

    public Pecahan(int a,int b,int c,int d)
    {
        id = UUID.randomUUID();
        pembilang1 = a;
        penyebut1 = b;
        pembilang2 = c;
        penyebut2 = d;
    }
    public UUID getId() {
        return id;
    }
    public int getPenyebut1()
    {
        return penyebut1;
    }
    public int getPembilang1()
    {
        return pembilang1;
    }
    public int getPenyebut2()
    {
        return penyebut2;
    }
    public int getPembilang2()
    {
        return pembilang2;
    }
    public void insert_to_array(boolean has_minus,char key,int pemb, int peny, int pembilang, int penyebut){
        String notes;
        if(has_minus) {
            pemb*=-1;
            pembilang*=-1;
        }
        if(pembilang>penyebut)
        {
            ans[0] = pembilang / penyebut;
            ans[1] = pembilang % penyebut;
            ans[2] = penyebut;
            if(has_minus) {
                pembilang*=-1;
                ans[0]*=-1;
            }
            if(ans[1]==0) {
                result = Integer.toString(pembilang1) + "/" + Integer.toString(penyebut1) + " " + key + " " + Integer.toString(pembilang2) + "/" + Integer.toString(penyebut2) + " = "
                        + Integer.toString(pembilang) + "/" + Integer.toString(penyebut) + " = " + Integer.toString(ans[0]);
                notes = "= "+Integer.toString(ans[0]);
                res_hitung.add(notes);
                res_pecahan.add("Hasil kalkulasi tidak mempunyai pecahan campuran");
            }
            else {
                result = Integer.toString(pembilang1) + "/" + Integer.toString(penyebut1) + " " + key + " " + Integer.toString(pembilang2) + "/" + Integer.toString(penyebut2) + " = "
                        + Integer.toString(pembilang) + "/" + Integer.toString(penyebut) + " = " + Integer.toString(ans[0]) + " + (" + Integer.toString(ans[1]) + "/" + Integer.toString(ans[2]) + ")";
                res_pecahan.add("1. Ambil Hasil Bagi antara pembilang dan penyebut menjadi nilai bilangan bulat");
                if(has_minus)
                    notes = "= (-1) * "+Integer.toString(pembilang*-1)+" / "+Integer.toString(penyebut) +" = "+Integer.toString(ans[0]);
                else
                    notes = "= "+Integer.toString(pembilang)+" / "+Integer.toString(penyebut) +" = "+Integer.toString(ans[0]);
                res_pecahan.add(notes);
                res_pecahan.add("2. Ambil Sisa Bagi antara pembilang dan penyebut menjadi nilai pembilang bilangan pecahan");
                if(has_minus)
                    notes = "= "+Integer.toString(pembilang*-1)+" % "+Integer.toString(penyebut) +" = "+Integer.toString(ans[1]);
                else
                    notes = "= "+Integer.toString(pembilang)+" % "+Integer.toString(penyebut) +" = "+Integer.toString(ans[1]);
                res_pecahan.add(notes);
                res_pecahan.add("3. Ambil penyebut menjadi nilai penyebut bilangan pecahan dan bentuklah format seperti ini : ");
                res_pecahan.add("a b/c");
                res_pecahan.add("a - > hasil bagi antara pembilang dan penyebut");
                res_pecahan.add("b - > sisa bagi antara pembilang dan penyebut");
                res_pecahan.add("c - > penyebut dari penyebut awal");
                notes = "= "+Integer.toString(ans[0])+" ( "+ans[1]+" / "+ans[2]+" )";
                res_pecahan.add(notes);
            }
        }
        else if(pembilang==penyebut)
        {
            ans[0]=1;
            if(has_minus) {
                pemb*=-1;
                ans[0]*=-1;
            }
            notes = "= "+Integer.toString(ans[0]);
            res_hitung.add(notes);
            res_pecahan.add("Hasil kalkulasi tidak mempunyai pecahan campuran");
            result = Integer.toString(pembilang1)+"/"+Integer.toString(penyebut1)+" "+key+" "+Integer.toString(pembilang2)+"/"+Integer.toString(penyebut2)+" = "
                    +Integer.toString(pemb)+"/"+Integer.toString(peny)+" = "+Integer.toString(ans[0]);
        }
        else if(pembilang==0){
            ans[0] = 0;
            notes = "= "+Integer.toString(ans[0]);
            res_hitung.add(notes);
            res_pecahan.add("Hasil kalkulasi tidak mempunyai pecahan campuran");
            result = Integer.toString(pembilang1)+"/"+Integer.toString(penyebut1)+" "+key+" "+Integer.toString(pembilang2)+"/"+Integer.toString(penyebut2)+" = "
                    +Integer.toString(pemb)+"/"+Integer.toString(peny)+" = "+Integer.toString(ans[0]);
        }
        else if(pemb == pembilang && peny==penyebut){
            if(has_minus) pemb*=-1;
            res_pecahan.add("Hasil kalkulasi tidak mempunyai pecahan campuran");
            result = Integer.toString(pembilang1)+"/"+Integer.toString(penyebut1)+" "+key+" "+Integer.toString(pembilang2)+"/"+Integer.toString(penyebut2)+" = "
                    +Integer.toString(pemb)+"/"+Integer.toString(peny);
        }
        else {
            ans[0] = pembilang;
            ans[1] = penyebut;
            if(has_minus) {
                pemb*=-1;
                ans[0]*=-1;
            }
            res_pecahan.add("Hasil kalkulasi tidak mempunyai pecahan campuran");
            if(penyebut==1)
                result = Integer.toString(pembilang1)+"/"+Integer.toString(penyebut1)+" "+key+" "+Integer.toString(pembilang2)+"/"+Integer.toString(penyebut2)+" = "
                        +Integer.toString(pemb)+"/"+Integer.toString(peny)+" = "+Integer.toString(ans[0]);
            else
                result = Integer.toString(pembilang1)+"/"+Integer.toString(penyebut1)+" "+key+" "+Integer.toString(pembilang2)+"/"+Integer.toString(penyebut2)+" = "
                        +Integer.toString(pemb)+"/"+Integer.toString(peny)+" = "+Integer.toString(ans[0])+"/"+Integer.toString(ans[1]);
        }
    }
    public void initPlus(){
        res_hitung.clear();
        res_pecahan.clear();
        int divider = lcm(penyebut1,penyebut2);
        String notes = "= "+Integer.toString(pembilang1)+"/"+Integer.toString(penyebut1)+" + "+Integer.toString(pembilang2)+"/"+Integer.toString(penyebut2);
        res_hitung.add(notes);
        int x = divider/penyebut1;
        int y = divider/penyebut2;
        int a = pembilang1*x;
        int b = pembilang2*y;
        notes = "= "+Integer.toString(pembilang1)+" * ("+x+") / "+Integer.toString(penyebut1)+" * ("+x+")"+" + "+Integer.toString(pembilang2)+" * ("+y+") / "+Integer.toString(penyebut2)+" * ("+y+")";
        res_hitung.add(notes);
        notes = "= "+Integer.toString(a)+"/"+Integer.toString(divider)+" + "+Integer.toString(b)+"/"+Integer.toString(divider);
        res_hitung.add(notes);
        int ans_pembilang = a+b;
        notes = "= "+Integer.toString(ans_pembilang)+"/"+Integer.toString(divider);
        res_hitung.add(notes);
        int pemb = ans_pembilang;
        int peny = divider;
        int tmp = gcd(ans_pembilang, divider);
        ans_pembilang = ans_pembilang / tmp;
        divider = divider / tmp;
        if(tmp>1) {
            notes = "= (" + Integer.toString(pemb) + " / (" + tmp + "))" + " / (" + Integer.toString(peny)+ " / (" + tmp + "))";
            res_hitung.add(notes);
            notes = "= "+Integer.toString(ans_pembilang)+"/"+Integer.toString(divider);
            res_hitung.add(notes);
        }
        insert_to_array(false,'+',pemb,peny,ans_pembilang,divider);
    }
    public void initMinus(){
        boolean minus = false;
        res_hitung.clear();
        res_pecahan.clear();
        int divider = lcm(penyebut1, penyebut2);
        String notes = "= "+Integer.toString(pembilang1)+"/"+Integer.toString(penyebut1)+" - "+Integer.toString(pembilang2)+"/"+Integer.toString(penyebut2);
        res_hitung.add(notes);
        int x = divider / penyebut1;
        int y = divider / penyebut2;
        int a = pembilang1 * x;
        int b = pembilang2 * y;
        notes = "= "+Integer.toString(pembilang1)+" * ("+x+") / "+Integer.toString(penyebut1)+" * ("+x+")"+" - "+Integer.toString(pembilang2)+" * ("+y+") / "+Integer.toString(penyebut2)+" * ("+y+")";
        res_hitung.add(notes);
        notes = "= "+Integer.toString(a)+"/"+Integer.toString(divider)+" - "+Integer.toString(b)+"/"+Integer.toString(divider);
        res_hitung.add(notes);
        int ans_pembilang = a - b;
        notes = "= "+Integer.toString(ans_pembilang)+"/"+Integer.toString(divider);
        res_hitung.add(notes);
        int pemb = ans_pembilang;
        int peny = divider;
        if (ans_pembilang < 0) {
            minus = true;
            ans_pembilang *= -1;
        }
        int tmp = gcd(ans_pembilang, divider);
        ans_pembilang = ans_pembilang / tmp;
        divider = divider / tmp;
        if(tmp>1) {
            notes = "= (" + Integer.toString(pemb) + " / (" + tmp + "))" + " / (" + Integer.toString(peny)+ " / (" + tmp + "))";
            res_hitung.add(notes);
            if(minus)
                notes = "= "+Integer.toString(ans_pembilang*-1)+"/"+Integer.toString(divider);
            else
                notes = "= "+Integer.toString(ans_pembilang)+"/"+Integer.toString(divider);
            res_hitung.add(notes);
        }
        if(minus) {
            ans_pembilang *= -1;
        }
        insert_to_array(minus,'-',pemb,peny,ans_pembilang,divider);
    }
    public void initTimes(){
        res_hitung.clear();
        res_pecahan.clear();
        String notes = "= "+Integer.toString(pembilang1)+"/"+Integer.toString(penyebut1)+" * "+Integer.toString(pembilang2)+"/"+Integer.toString(penyebut2);
        res_hitung.add(notes);
        int divider = penyebut1 * penyebut2;
        int ans_pembilang = pembilang1 * pembilang2;
        int pemb = ans_pembilang;
        int peny = divider;
        notes = "= "+Integer.toString(ans_pembilang)+"/"+Integer.toString(divider);
        res_hitung.add(notes);
        int tmp = gcd(ans_pembilang, divider);
        ans_pembilang = ans_pembilang / tmp;
        divider = divider / tmp;
        if(tmp>1) {
            notes = "= (" + Integer.toString(pemb) + " / (" + tmp + "))" + " / (" + Integer.toString(peny)+ " / (" + tmp + "))";
            res_hitung.add(notes);
            notes = "= "+Integer.toString(ans_pembilang)+"/"+Integer.toString(divider);
            res_hitung.add(notes);
        }
        insert_to_array(false,'*',pemb,peny,ans_pembilang,divider);
    }
    public void initDivide(){
        res_hitung.clear();
        res_pecahan.clear();
        int divider = pembilang2 * penyebut1;
        int ans_pembilang = pembilang1 * penyebut2;
        String notes = "= "+Integer.toString(pembilang1)+"/"+Integer.toString(penyebut1)+" / "+Integer.toString(pembilang2)+"/"+Integer.toString(penyebut2);
        res_hitung.add(notes);
        notes = "= "+Integer.toString(pembilang1)+"/"+Integer.toString(penyebut1)+" * "+Integer.toString(penyebut2)+"/"+Integer.toString(pembilang2);
        res_hitung.add(notes);
        int pemb = ans_pembilang;
        int peny = divider;
        notes = "= "+Integer.toString(ans_pembilang)+"/"+Integer.toString(divider);
        res_hitung.add(notes);
        int tmp = gcd(ans_pembilang, divider);
        ans_pembilang = ans_pembilang / tmp;
        divider = divider / tmp;
        if(tmp>1) {
            notes = "= (" + Integer.toString(pemb) + " / (" + tmp + "))" + " / (" + Integer.toString(peny)+ " / (" + tmp + "))";
            res_hitung.add(notes);
            notes = "= "+Integer.toString(ans_pembilang)+"/"+Integer.toString(divider);
            res_hitung.add(notes);
        }
        insert_to_array(false,'/',pemb,peny,ans_pembilang,divider);
    }
    public String getResult(){
        return result;
    }
    public ArrayList<String> getCaraHitung(){
        return res_hitung;
    }
    public ArrayList<String> getCaraBagi(){
        return res_pecahan;
    }
}
