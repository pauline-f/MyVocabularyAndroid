package com.example.pauline.myvocabulary.model;

import java.util.Random;

public class QuizzWordTranslation extends Quizz {

    public QuizzWordTranslation(ListWord list) {
        this.listWord = list;
    }

    @Override
    public String displayWord() {
        Random randomWord = new Random();
        int index= randomWord.nextInt(listWord.numberOfWords());
        currentWord = listWord.getAWord(index);
        return currentWord.getWord();
    }

    @Override
    public String getGoodAnswer() {
        return currentWord.getTranslation();
    }

    @Override
    public boolean isGoodAnswer(String input) {
        return currentWord.getTranslation().equals(input);
    }
}
