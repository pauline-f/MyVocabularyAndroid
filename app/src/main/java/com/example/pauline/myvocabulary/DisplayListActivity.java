package com.example.pauline.myvocabulary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class DisplayListActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    Spinner spinner;

    AllLists allLists = new AllLists();
    ListWord list;
    FileClass file = new FileClass();
    Vector choices = new Vector<String>();
    String valueSpinner;
    ListView listView;

    ArrayList<String> words;
    ArrayList<String> translations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        //if (file != null) {

            file.readFromFile(this);

            allLists = file.RecupAllLists();


            for (int i = 0; i < allLists.countLists(); i++) {
                String name = allLists.lists.get(i).getName();
                choices.add(name);
            }

            spinner = findViewById(R.id.allLists);
            adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.choices);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            valueSpinner = (String) spinner.getItemAtPosition(spinner.getSelectedItemPosition());

            list = allLists.findAList(valueSpinner);

            //Add the words in the arrayList
            arrayWordTranslation();

            listView = (ListView) findViewById(R.id.listWords);
            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), words, translations);
            listView.setAdapter(customAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                    //ListView
                    listView = (ListView) findViewById(R.id.listWords);

                    valueSpinner = (String) spinner.getItemAtPosition(spinner.getSelectedItemPosition());

                    list = allLists.findAList(valueSpinner);

                    //Add the words in the arrayList
                    arrayWordTranslation();

                    CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), words, translations);
                    listView = (ListView) findViewById(R.id.listWords);
                    listView.setAdapter(customAdapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
    }

    public void arrayWordTranslation() {
        words = new ArrayList<>();
        translations = new ArrayList<>();
        for (int i = 0; i < list.numberOfWords(); i++) {
            Word word = list.getWord(i);
            words.add(word.getWord());
            translations.add(word.getTranslation());
        }
    }

}

