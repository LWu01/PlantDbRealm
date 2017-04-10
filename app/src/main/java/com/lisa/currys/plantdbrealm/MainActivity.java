package com.lisa.currys.plantdbrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    // RealmModel rm = realm.createObject(RealmModel.class);

    Button add, view, update, delete;
    EditText entry_no, input;
    TextView text;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.addBt);
        view = (Button) findViewById(R.id.viewBt);
        update = (Button) findViewById(R.id.updateBt);
        delete = (Button) findViewById(R.id.delBtn);
        entry_no = (EditText) findViewById(R.id.entry_no);
        input = (EditText) findViewById(R.id.input);
        text = (TextView) findViewById(R.id.text);

        //initialize realm
        Realm.init(this);

        //To get realm instance use getDefaultInstance();
        Realm realm = Realm.getDefaultInstance();
    }

    public void clickAction(View view) {
        switch (view.getId()) {
            case R.id.addBt:
                addRecord();
                break;
            case R.id.viewBt:
                viewRecord();
                break;
            case R.id.updateBt:
                updateRecord();
                break;
            case R.id.delBtn:
                deleteRecord();
        }
    }

    public void addRecord() {
        //Writing data
        realm.beginTransaction();

        RealmModel rm = realm.createObject(RealmModel.class);
        //get entry no. from user input
        rm.setEntry_no(Integer.parseInt(entry_no.getText().toString()));
        rm.setInput(input.getText().toString());
        realm.commitTransaction();

    }
    public void viewRecord() {
        //Reading data
        RealmResults<RealmModel> results = realm.where(RealmModel.class).findAll();

        text.setText("");

        for (RealmModel realmModel : results) {
            text.append(realmModel.getEntry_no() + " " + realmModel.getInput() + "\n");
        }
        //Alternate method for fetching results
        //RealmResults<RealmModel>results1=realm.where(RealmModel.class).equalTo("roll_no", 20).findAll();

    }
    public void updateRecord() {
        //Updating data
        RealmResults<RealmModel> results = realm.where(RealmModel.class).equalTo("entry_no", Integer.parseInt(entry_no.getText().toString())).findAll();

        realm.beginTransaction();

        for (RealmModel realmModel : results) {
            realmModel.setInput(input.getText().toString());
        }
        realm.commitTransaction();
    }
    public void deleteRecord() {
        //Delete data
        RealmResults<RealmModel> results = realm.where(RealmModel.class).equalTo("entry_no", Integer.parseInt(entry_no.getText().toString())).findAll();

        realm.beginTransaction();

        //Remove single match
        //results.deleteFirstFromRealm();
        //results.deleteLastFromRealm();

        //remove single object
        //RealmModel realmModel = results.get(5);
        //realmModel.deleteFromRealm();

        //Delete all matches
        results.deleteAllFromRealm();

        realm.commitTransaction();
    }
    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
