package com.lisa.currys.plantdbrealm;

import io.realm.RealmObject;

/**
 * Created by CURRYS on 10/04/2017.
 */

/**
 * To add a variable primary key, add @PrimaryKey annotation before it
 */

public class RealmModel extends RealmObject {

    private int entry_no;
    private String input;

    public int getEntry_no(){
        return entry_no;
    }

    public void setEntry_no(int entry_no){
        this.entry_no = entry_no;
    }

    public String getInput(){
        return input;
    }

    public void setInput(String input){
        this.input = input;
    }
}
