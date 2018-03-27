package com.example.adam.rss;

/**
 * Created by Adam on 08/03/2018.
 *
 * Student ID:S1436108
 * Adam Hardie
 */

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    RadioButton rButon0;
    RadioButton rButton1;
    ViewFlipper VF;
    ListView plannedLV;
    SearchView sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(getPackageName(), "just before avw");


        //Using the predefined variables for each
        rButon0 = (RadioButton) findViewById(R.id.r0);
        rButton1 = (RadioButton) findViewById(R.id.r1);
        VF = (ViewFlipper) findViewById(R.id.ViewFlipper01);
        plannedLV = (ListView) findViewById(R.id.planned); // LIST VIEW
        sv=(SearchView) findViewById(R.id.searchView1);//Search View, used for search feature, work on the


        rButon0.setOnClickListener(radio_listener);
        rButton1.setOnClickListener(radio_listener);


        //RoadworksCurrParse --- This will get the current incidents on the roads
        ArrayList<String> headlines = new ArrayList<>();
        RoadworksCurrParse getXML = new RoadworksCurrParse();
        getXML.execute();
        headlines = getXML.heads();

        //RoadWorksPlanParse --- This will get all of the planned roadworks
        ArrayList<String> headlines2 = new ArrayList<>();
        RoadWorksPlanParse getXML2 = new RoadWorksPlanParse();
        getXML2.execute();
        headlines2 = getXML2.heads();

        plannedLV.setTextFilterEnabled(true); // THIS was supposed to help with the search functionality but in hindsight it turned out to do nothing
        // Binding data
        final ArrayAdapter CurrentRW = new ArrayAdapter(this, android.R.layout.simple_list_item_1, headlines);
        final ArrayAdapter PlannedRW = new ArrayAdapter(this, android.R.layout.simple_list_item_1, headlines2);
        setListAdapter(CurrentRW);
        plannedLV.setAdapter(PlannedRW);



        plannedLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                                 @Override
                                                 public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                     Intent browserIntent = new Intent(
                                                             Intent.ACTION_VIEW,
                                                             Uri.parse("https://twitter.com/trafficscotland"));
                                                     startActivity(browserIntent);
                                                     return false;
                                                 }
                                             });

                //This section covers the search feature of the application, there was a issues I had with this code but I got it working in the end.
                sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String text) {
                        PlannedRW.getFilter().filter(text.trim());
                        CurrentRW.getFilter().filter(text.trim());
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String text) {

                        PlannedRW.getFilter().filter(text.trim());
                        CurrentRW.getFilter().filter(text.trim());
                        return false;
                    }
                });



    }



//Viewflipper for each radiobutton, this is responsible for the interface changes with the onclick of each radio button.
    private View.OnClickListener radio_listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.r0:
                    VF.setDisplayedChild(0);

                    break;
                case R.id.r1:
                    VF.setDisplayedChild(1);


                    break;
            }
        }
    };
}
