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

class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> words;
    ArrayList<String > translations;
    LayoutInflater inflater;

    public CustomAdapter (Context context, ArrayList<String> words, ArrayList<String> translations) {
        this.context = context;
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
        view = inflater.inflate(R.layout.activity_list_view, null);
        TextView textWords = (TextView) view.findViewById(R.id.textView);
        TextView textTranslation = (TextView) view.findViewById(R.id.translationView);
        textWords.setText(words.get(i));
        textTranslation.setText(translations.get(i));
        return view;
    }
}
