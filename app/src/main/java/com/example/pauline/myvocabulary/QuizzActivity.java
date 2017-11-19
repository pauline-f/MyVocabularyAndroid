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

import com.example.pauline.myvocabulary.model.AllLists;
import com.example.pauline.myvocabulary.model.ListWord;
import com.example.pauline.myvocabulary.model.Quizz;
import com.example.pauline.myvocabulary.model.QuizzFactory;

import java.util.Vector;

public class QuizzActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    Spinner spinnerList;
    Spinner spinnerQuizz;
    String valueSpinnerList;
    String valueSpinnerQuizz;

    AllLists allLists = new AllLists();
    FileManager file = new FileManager();
    Vector choices = new Vector<String>();
    String[] quizzes = {"QuizzWordTranslation", "QuizzTranslationWord"};
    Quizz quizz;

    /**
     *  Adds the names of the list of words in the spinnerList.
     *  Adds the 2 quizzes in the spinnerQuizz.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        file.readFromFile(this);

        allLists = file.getAllLists();

        for (int i = 0; i < allLists.countLists(); i++) {
            String name = allLists.lists.get(i).getName();
            choices.add(name);
        }

        spinnerList = findViewById(R.id.allLists);
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.choices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerList.setAdapter(adapter);

        spinnerQuizz = findViewById(R.id.allQuizz);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.quizzes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuizz.setAdapter(adapter);
    }

    /**
     *  Allows to play a quizz based on the list and the quizz type selected
     * @param view
     */
    public void playQuizz(View view) {

        valueSpinnerList = (String) spinnerList.getItemAtPosition(spinnerList.getSelectedItemPosition());
        valueSpinnerQuizz = (String) spinnerQuizz.getItemAtPosition(spinnerQuizz.getSelectedItemPosition());

        ListWord listWord = allLists.findAList(valueSpinnerList);

        if (listWord != null && !valueSpinnerQuizz.isEmpty()) {
            quizz = QuizzFactory.getQuizz(valueSpinnerQuizz, listWord);
            startQuizz(quizz);
            LinearLayout layout = findViewById(R.id.layoutPlay);
            layout.setVisibility(View.VISIBLE);
        }
        else {
            Toast.makeText(getApplicationContext(), "You must select a list and a quizz!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Displays the word for the quizz
     * @param quizz
     */
    public void startQuizz(Quizz quizz) {
        String wordQuizz = quizz.displayWord();
        EditText question = findViewById(R.id.question);
        question.setText(wordQuizz);
    }

    /**
     *  Checks if the word is right
     * @param view
     */
    public void checkWord(View view) {
        EditText answer = findViewById(R.id.answer);
        String valueAnswer = answer.getText().toString().trim().toLowerCase();
        if (quizz.isGoodAnswer(valueAnswer)) {
            displayAlert("Good answer!");
        } else {
            displayAlert("The answer was: " + quizz.getGoodAnswer());
        }
        answer.setText("");
        startQuizz(quizz);
    }

    public void displayAlert(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(message);
        alert.setCancelable(true);
        alert.show();
    }
}
