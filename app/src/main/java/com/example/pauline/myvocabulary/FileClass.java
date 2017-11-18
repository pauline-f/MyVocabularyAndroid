package com.example.pauline.myvocabulary;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by pauline on 07/11/2017.
 */

public class FileClass{

    private static final String TAG = FileClass.class.getName();
    private static final String FILENAME = "words.csv";
    AllLists lists = new AllLists();

    public void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_APPEND));
            outputStreamWriter.write(data + "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e(TAG, "File write failed: " + e.toString());
        }
    }


    public String readFromFile(Context context) {
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(FILENAME);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {

                    String[] wordFile = receiveString.split(";");
                    Word word = new Word(wordFile[1], wordFile[2], 0, 0);


                    if (!lists.lists.isEmpty()) {
                        boolean newList = false;
                        int i = 0;

                        while (i < lists.lists.size() && !newList) {
                            if (lists.lists.get(i).getName().equals(wordFile[0])) {
                                newList = true;
                            } else {
                                i++;
                            }
                        }

                        if (newList == false) {
                            addNewListWithWord(wordFile[0], word);
                        } else {
                            lists.lists.get(i).addWord(word);
                        }
                    } else {
                        addNewListWithWord(wordFile[0], word);
                    }
                }

                inputStream.close();
                ret = stringBuilder.toString().trim();
            }
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "File not found: " + e.toString());
        }
        catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }
        return ret;
    }

    public AllLists RecupAllLists() {
        return lists;
    }

    public void addNewListWithWord(String newList, Word newWord) {
        ListWord list = new ListWord(newList);
        list.addWord(newWord);
        lists.addList(list);
    }

}