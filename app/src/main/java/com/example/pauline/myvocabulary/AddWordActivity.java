package com.example.pauline.myvocabulary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pauline.myvocabulary.model.AllLists;
import com.example.pauline.myvocabulary.model.ListWord;
import com.example.pauline.myvocabulary.model.Word;

import java.util.Vector;

public class AddWordActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    Spinner spinner;

    AllLists allLists = new AllLists();
    FileManager file = new FileManager();
    Vector choices = new Vector<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        file.readFromFile(this);

        allLists = file.getAllLists();

        for (int i = 0; i < allLists.countLists(); i++) {
            String name = allLists.lists.get(i).getName();
            choices.add(name);
        }

        spinner = findViewById(R.id.allLists);
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.choices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void addList(View view) {
        EditText input = findViewById(R.id.listName);
        String name = input.getText().toString().toLowerCase().trim();

        if (allLists.findList(name)) {
            Toast.makeText(getApplicationContext(), "This list already exists!", Toast.LENGTH_SHORT).show();
        } else {
            ListWord list = new ListWord(name);
            allLists.addList(list);
            choices.add(list.getName());

            spinner = (Spinner) findViewById(R.id.allLists);
            adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, choices);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
    }

    public void saveWord(View view) {
        EditText inputWord = findViewById(R.id.word);
        EditText inputTranslation = findViewById(R.id.translation);

        String inputW = inputWord.getText().toString().trim().toLowerCase();
        String inputT = inputTranslation.getText().toString().trim().toLowerCase();

        if (inputW.isEmpty() || inputT.isEmpty()) {
            Toast.makeText(getApplicationContext(), "You must write a word and a translation!", Toast.LENGTH_SHORT).show();
        }
        else {
            Spinner spinner = findViewById(R.id.allLists);
            String valueSpinner = (String) spinner.getItemAtPosition(spinner.getSelectedItemPosition());

            Toast.makeText(getApplicationContext(), valueSpinner, Toast.LENGTH_SHORT).show();

            ListWord listWord = allLists.findAList(valueSpinner);

            if (listWord.findWord(inputW)) {
                //Display that the word can't be save, it already exists
                Toast.makeText(getApplicationContext(), "This word already exists!", Toast.LENGTH_SHORT).show();
                inputWord.setText("");
                inputTranslation.setText("");
            }
            else {
                Word word = new Word(inputW, inputT);
                listWord.addWord(word);

                String saveData = valueSpinner + ";" + inputW + ";" + inputT;

                //Write in the file the name of the list, the word and the translation
                file.writeToFile(saveData, this);

                Toast.makeText(getApplicationContext(), "New word: " + inputW, Toast.LENGTH_SHORT).show();

                //Initialisation EditText
                inputWord.setText("");
                inputTranslation.setText("");
            }
        }
    }
}
