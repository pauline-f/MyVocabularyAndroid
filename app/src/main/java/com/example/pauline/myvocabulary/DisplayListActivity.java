package com.example.pauline.myvocabulary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.pauline.myvocabulary.model.AllLists;
import com.example.pauline.myvocabulary.model.ListWord;
import com.example.pauline.myvocabulary.model.Word;

import java.util.ArrayList;
import java.util.Vector;

public class DisplayListActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    Spinner spinner;

    AllLists allLists = new AllLists();
    ListWord list;
    FileManager file = new FileManager();
    Vector choices = new Vector<String>();
    String valueSpinner;
    ListView listView;

    ArrayList<String> words;
    ArrayList<String> translations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        file.readFromFile(this);

        allLists = file.getAllLists();

        if (allLists.countLists() == 0) {
            return;
        }

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
        loadWordsAndTranslationsIntoArray();

        listView = (ListView) findViewById(R.id.listWords);
        CustomAdapter customAdapter = new CustomAdapter(this, words, translations);
        listView.setAdapter(customAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                listView = findViewById(R.id.listWords);

                valueSpinner = (String) spinner.getItemAtPosition(spinner.getSelectedItemPosition());

                list = allLists.findAList(valueSpinner);

                //Load the words in the arrayList
                loadWordsAndTranslationsIntoArray();

                CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), words, translations);
                listView = (ListView) findViewById(R.id.listWords);
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }

    /**
     *  Loads the words and the translation in an array for the customAdapter
     */
    public void loadWordsAndTranslationsIntoArray() {
        words = new ArrayList<>();
        translations = new ArrayList<>();
        for (int i = 0; i < list.numberOfWords(); i++) {
            Word word = list.getWord(i);
            words.add(word.getWord());
            translations.add(word.getTranslation());
        }
    }

}

