package com.example.pauline.myvocabulary;

/**
 * Created by pauline on 16/11/2017.
 */

import java.util.Random;

public class QuizzTranslationWord extends Quizz {

    public QuizzTranslationWord(ListWord list) {
        this.listWord = list;
    }

    @Override
    public String displayWord() {
        Random randomWord = new Random();
        int index= randomWord.nextInt(listWord.numberOfWords());
        currentWord = listWord.getAWord(index);
        return currentWord.getTranslation();
    }

    @Override
    public String getGoodAnswer() {
        return currentWord.getWord();
    }

    @Override
    public boolean isGoodAnswer(String input) {
        return currentWord.getWord().equals(input);
    }
}
