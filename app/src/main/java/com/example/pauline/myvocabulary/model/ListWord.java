package com.example.pauline.myvocabulary.model;

/**
 * Created by pauline on 02/11/2017.
 */

import java.util.ArrayList;

/**
 *  This class contains the list of Word and the list name.
 */
public class ListWord {
    private ArrayList<Word> listWord;
    private String name;

    public ListWord(String name) {
        listWord = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addWord(Word word) {
        listWord.add(word);
    }

    public int numberOfWords() {
        return listWord.size();
    }

    public String displayNumberOfWords() {
        return "The list " + name + " has " + numberOfWords() + " words.";
    }

    public boolean removeWord(int index) {
        if (index >= 0 && index < listWord.size()) {
            listWord.remove(index);
            return true;
        }
        else {
            System.out.println("You must write the number of the word.");
            return false;
        }
    }

    public boolean findWord(String word) {
        int i = 0;
        boolean find = false;
        while (i < listWord.size() && !find) {
            if (listWord.get(i).getWord().equals(word)) {
                find = true;
            }
            i++;
        }
        if (find == true) {
            return true;
        } else {
            return false;
        }
    }

    public String displayAllWords() {
        StringBuilder display = new StringBuilder();
        for (Word word: listWord) {
            display.append(word.getWordAsString()).append("\n");
        }
        return display.toString();
    }

    public String displayAllWordsWithIndex() {
        StringBuilder display = new StringBuilder();
        int index = 1;
        for (int i = 0; i < listWord.size(); i++) {
            display.append(index + " - " + listWord.get(i).getWord() + " - " + listWord.get(i).getTranslation()).append("\n");
            index++;
        }
        return display.toString();
    }

    public String displayAllWordWithScore() {
        StringBuilder words = new StringBuilder();
        for (Word word: listWord) {
            words.append(word.getWordWithScore()).append("\n");
        }
        return words.toString();
    }



    public Word getAWord(int index) {
        return listWord.get(index);
    }

    /**
     * @param index The index of the Word to be returned. It has to be a valid index, otherwise it will throw an exception.
     * @return the Word formatted for CSV
     */
    public String getWordAsCSV(int index) {
        Word word = listWord.get(index);
        return word.getWord() + ";" + word.getTranslation() + ";" + word.getScore() + ";" + word.getCount();
    }

    public Word getWord(int index) {
        Word word = listWord.get(index);
        return word;
    }
}
