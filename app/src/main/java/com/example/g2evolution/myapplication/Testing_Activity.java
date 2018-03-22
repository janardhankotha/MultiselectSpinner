package com.example.g2evolution.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Testing_Activity extends AppCompatActivity implements com.example.g2evolution.myapplication.MultiSelectionSpinner.OnMultipleItemsSelectedListener {

    ArrayList<String> worldlist7;//courses
    ArrayList<String> worldlist8;//courses
    ArrayList<coursespojo> world6;//couses
    MultiSelectionSpinner Courses;
    JSONObject jsonobject;
    JSONArray jsonarray;
    ArrayList<multispinnerpojo> world5;//education
    ArrayList<multispinnerpojo> world4;
    private  ArrayList<String> worldlist4;
    ArrayList<String> worldlist5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        Courses = (MultiSelectionSpinner) findViewById(R.id.courses);
        new DownloadJSON4().execute();

    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {

    }

    @Override
    public void selectedStrings2(List<String> strings) {

    }
    private class DownloadJSON4 extends AsyncTask<Void, Void, Void> {
        Context context;
        @Override
        protected Void doInBackground(Void... params) {
            // Locate the WorldPopulation Class
            world4 = new ArrayList<multispinnerpojo>();
            // Create an array to populate the spinner
            worldlist4 = new ArrayList<String>();
            worldlist5 = new ArrayList<String>();

            // JSON file URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL("http://www.trywis.com/app/trywis_dropdowns.php");


            try {
                // Locate the NodeList name
                jsonarray = jsonobject.getJSONArray("wish_details");
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonobject = jsonarray.getJSONObject(i);

                    //WorldPopulation worldpop = new WorldPopulation();
                    multispinnerpojo multispinner = new multispinnerpojo();

                    multispinner.setId(jsonobject.optInt("wish_id"));
                    multispinner.setName(jsonobject.optString("wish_name"));
				/*	worldpop.setPopulation(jsonobject.optString("population"));
					worldpop.setFlag(jsonobject.optString("flag"));*/
                    world4.add(multispinner);

                    // Populate spinner with country names
                    worldlist4.add(jsonobject.optString("wish_name"));
                    worldlist5.add(jsonobject.optString("wish_id"));

                }
            } catch (Exception e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }


            return null;

        }

        @Override
        protected void onPostExecute(Void args) {
            // Locate the spinner in activity_main.xml
            // Spinner mySpinner = (Spinner) findViewById(R.id.my_spinner);


            MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.courses);
            multiSelectionSpinner.setItems(worldlist4);
            multiSelectionSpinner.setItemsid(worldlist5);
            // multiSelectionSpinner.setSelection(new int[]{2, 6});
            multiSelectionSpinner.setListener(Testing_Activity.this);




        }
    }






}
