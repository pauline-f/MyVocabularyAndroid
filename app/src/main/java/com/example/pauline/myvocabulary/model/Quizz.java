package com.example.pauline.myvocabulary.model;

/**
 * Created by pauline on 16/11/2017.
 */

/**
 *  This abstract class allows to have methods about quizz: display a word, check the answer, display the good answer and
 */
public abstract class Quizz {
    ListWord listWord;
    Word currentWord;

    /**
     * Display a word or a translation
     * @return a String with a word or a translation
     */
    public abstract String displayWord();

    /**
     * Check if the input if the good word or the good translation
     * @param input
     * @return boolean
     */
    public abstract boolean isGoodAnswer(String input);

    /**
     * Return the word or the translation which is the good answer
     * @return
     */
    public abstract String getGoodAnswer();

    /**
     * Increment count each time the user write the answer
     */
    public void incrementCount() {
        currentWord.incrementCount();
    }

    /**
     * Increment score each time the user has a good answer
     */
    public void incrementScore() {
        currentWord.incrementScore();
    }
}
