package com.kolexia.www.simplemath;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Created by mike on 11/27/15.
 */
public class SingletonAngka {
    private static SingletonAngka c;
    private static Context context;
    private static ArrayList<Pecahan> angka = new ArrayList<Pecahan>();
    public static ArrayList<Integer> tmp = new ArrayList<Integer>();
    private SingletonAngka(Context appContext){
        context = appContext;
        int a,b,c,d;
        for(int j=1;j<=4;j++)
        {
            a=b=c=d=0;
            for(int k=1;k<=4;k++)
            {
                Random rand = new Random();
                a = rand.nextInt(100);
                b = rand.nextInt(100);
                c = rand.nextInt(100);
                d = rand.nextInt(100);
            }
            Pecahan p = new Pecahan(a,b,c,d);
            angka.add(p);
        }
    }

    public static SingletonAngka get(Context con){
        if (c == null){
            c = new SingletonAngka(con.getApplicationContext());
        }
        return c;
    }

    public ArrayList<Pecahan> getAllAngka() {
        return angka;
    }

    public Pecahan getAngka(UUID id){
        for(Pecahan c : angka){
            if (c.getId() == id){
                return c;
            }
        }
        return null;
    }
}
