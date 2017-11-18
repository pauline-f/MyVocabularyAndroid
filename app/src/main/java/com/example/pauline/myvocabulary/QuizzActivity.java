package com.example.pauline.myvocabulary;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Vector;

public class QuizzActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    Spinner spinnerList;
    Spinner spinnerQuizz;
    String valueSpinnerList;
    String valueSpinnerQuizz;

    AllLists allLists = new AllLists();
    FileClass file = new FileClass();
    Vector choices = new Vector<String>();
    String[] quizzes = {"QuizzWordTranslation", "QuizzTranslationWord"};
    Quizz quizz = new Quizz() {
        @Override
        public String displayWord() {
            return null;
        }

        @Override
        public boolean isGoodAnswer(String input) {
            return false;
        }

        @Override
        public String getGoodAnswer() {
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        file.readFromFile(this);

        allLists = file.RecupAllLists();

        for (int i = 0; i < allLists.countLists(); i++) {
            String name = allLists.lists.get(i).getName();
            choices.add(name);
        }

        //Spinner list
        spinnerList = findViewById(R.id.allLists);
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.choices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerList.setAdapter(adapter);

        //Spinner Quizz
        spinnerQuizz = findViewById(R.id.allQuizz);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.quizzes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuizz.setAdapter(adapter);
    }

    public void playQuizz(View view) {

        //Value spinner list
        valueSpinnerList = (String) spinnerList.getItemAtPosition(spinnerList.getSelectedItemPosition());

        //Value spinner quizz
        valueSpinnerQuizz = (String) spinnerQuizz.getItemAtPosition(spinnerQuizz.getSelectedItemPosition());

        ListWord listWord = allLists.findAList(valueSpinnerList);


        if (listWord != null || valueSpinnerQuizz == "") {
            quizz = QuizzFactory.getQuizz(valueSpinnerQuizz, listWord);
            quizz(quizz);
            LinearLayout layout = findViewById(R.id.layoutPlay);
            layout.setVisibility(View.VISIBLE);
        }
        else {
            Toast.makeText(getApplicationContext(), "You must select a list and a quizz!", Toast.LENGTH_SHORT).show();
        }
    }


    public void quizz(Quizz quizz) {
        String wordQuizz = quizz.displayWord();
        //Display the word
        EditText question = findViewById(R.id.question);
        question.setText(wordQuizz);
    }


    public void checkWord(View view) {
        EditText answer = findViewById(R.id.answer);
        String valueAnswer = answer.getText().toString().trim().toLowerCase();
        if (quizz.isGoodAnswer(valueAnswer)) {
            //Toast.makeText(getApplicationContext(), "Good answer!", Toast.LENGTH_SHORT).show();
            displayAlert("Good answer!");
        } else {
            //Toast.makeText(getApplicationContext(), "The answer was: " + quizz.getGoodAnswer(), Toast.LENGTH_SHORT).show();
            displayAlert("The answer was: " + quizz.getGoodAnswer());
        }
        answer.setText("");
        quizz(quizz);
    }

    public void displayAlert(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(message);
        alert.setCancelable(true);
        alert.show();
    }
}
