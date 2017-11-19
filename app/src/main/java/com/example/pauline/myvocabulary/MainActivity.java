package com.example.pauline.myvocabulary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    FileManager file = new FileManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fileData = file.readFromFile(this);
        Log.d("data", fileData);
    }

    public void displayWordActivity(View view) {
        Intent intent = new Intent(this, DisplayListActivity.class);
        startActivity(intent);
    }

    public void addWordActivity(View view) {
        Intent intent = new Intent(this, AddWordActivity.class);
        startActivity(intent);
    }

    public void quizzActivity(View view) {
        Intent intent = new Intent(this,QuizzActivity.class);
        startActivity(intent);
    }
}
