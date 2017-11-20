package com.example.pauline.myvocabulary;

import android.content.Context;

import android.util.Log;


import com.example.pauline.myvocabulary.model.AllLists;
import com.example.pauline.myvocabulary.model.ListWord;
import com.example.pauline.myvocabulary.model.Word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *  Writes and reads the file "app.csv".
 *  When it writes, it writes the object data in the file.
 *  When it reads, it reads the file and puts the data in the objects.
 */

public class FileManager {

    private static final String TAG = FileManager.class.getName();
    private static final String FILENAME = "words.csv";
    AllLists allLists = new AllLists();

    /**
     *  Writes the data from the classes AllLists, ListWord and Word in the csv file
     * @param data
     * @param context
     */
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

    /**
     *  Reads the csv file. Add the data in the classes AllLists, ListWord and Word
     * @param context
     * @return
     */
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
                    Word word = new Word(wordFile[1], wordFile[2]);


                    if (!allLists.lists.isEmpty()) {
                        boolean newList = false;
                        int i = 0;

                        while (i < allLists.lists.size() && !newList) {
                            if (allLists.lists.get(i).getName().equals(wordFile[0])) {
                                newList = true;
                            } else {
                                i++;
                            }
                        }

                        if (!newList) {
                            addNewListWithWord(wordFile[0], word);
                        } else {
                            allLists.lists.get(i).addWord(word);
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

    public AllLists getAllLists() {
        return allLists;
    }

    public void addNewListWithWord(String newList, Word newWord) {
        ListWord list = new ListWord(newList);
        list.addWord(newWord);
        allLists.addList(list);
    }
}