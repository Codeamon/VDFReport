package com.ayoka.test;

import java.util.ArrayList;

/**
 * Created by OsmanKorcan on 28.4.2016.
 */
public class TestClass_ReportTab1 {

    private ArrayList<String> labels = new ArrayList<String>();
    private ArrayList<String> numbervalues = new ArrayList<String>();

    public TestClass_ReportTab1()
    {
        numbervalues = new ArrayList<String>();
        labels = new ArrayList<String>();

        labels.add("Ekim");
        labels.add("Kasım");
        labels.add("Aralık");
        labels.add("Ocak");
        labels.add("Şubat");
        labels.add("Mart");
        labels.add("Nisan");

        numbervalues.add("4050");
        numbervalues.add("8980");
        numbervalues.add("6045");
        numbervalues.add("12916");
        numbervalues.add("1840");
        numbervalues.add("9240");
        numbervalues.add("6040");
    }
    public ArrayList<String> getList()
    {
        return labels;
    }

    public ArrayList getNumbervalues()
    {
        return numbervalues;
    }

    public int getValueById(int id)
    {
        return Integer.parseInt(numbervalues.get(id)) ;
    }

    public String getDataById(int id)
    {
        return labels.get(id);
    }




}
