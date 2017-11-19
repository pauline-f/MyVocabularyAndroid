package com.example.pauline.myvocabulary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pauline on 14/11/2017.
 */

/**
 *  Allows to have 2 columns in the listView to display the word and the translation
 */

class CustomAdapter extends BaseAdapter {
    private ArrayList<String> words;
    private ArrayList<String > translations;
    private LayoutInflater inflater;

    public CustomAdapter (Context context, ArrayList<String> words, ArrayList<String> translations) {
        this.words = words;
        this.translations = translations;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.display_word, null);
        TextView textWords = (TextView) view.findViewById(R.id.textView);
        TextView textTranslation = (TextView) view.findViewById(R.id.translationView);
        textWords.setText(words.get(i));
        textTranslation.setText(translations.get(i));
        return view;
    }
}
