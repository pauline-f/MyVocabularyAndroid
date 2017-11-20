package com.example.pauline.myvocabulary.model;

/**
 *  This class contains a word, its translation, and keeps track of the score.
 *
 */
public class Word {
    private String word;
    private String translation;
    private int count;
    private int score;

    public Word(String word, String translation) {
        this.word = word;
        this.translation = translation;
        this.score = 0;
        this.count = 0;
    }

    public Word(String word, String translation, int score, int count) {
        this.word = word;
        this.translation = translation;
        this.score = score;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public int getCount() {
        return count;
    }

    public int getScore() {
        return score;
    }

    public String getWordAsString() {
        return word + " - " + translation;
    }

    /**
     *  Increments the number of time this word was shown in a quizz
     */
    public void incrementCount() {
        this.count++;
    }

    /**
     *  Increments the number of time the user writes the good answer in a quizz
     */
    public void incrementScore() {
        this.score++;
    }

    public String getWordWithScore() {
        double percentage = 0;
        if (count != 0) {
            percentage = score / count * 100;
        }
        return word + " - " + translation + " - Score: " + score + " / " + count + " - " + percentage + "%";
    }
}
