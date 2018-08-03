package com.desai.vatsal.mydynamiccalendarlibrary;

public class ListData {
    private String Name;
    private int WFID;

    public ListData(String name){

        this.Name = name;

    }

    public ListData(int wfid,String name){
        this.WFID=wfid;
        this.Name = name;

    }

    public String getTitle(){
        return  Name;
    }

    public void setTitle(String name){
        this.Name=name;
    }

    public Integer getWFID(){
        return WFID;
    }

    public void setWFID(Integer WFID){
        this.WFID=WFID;
    }
}
