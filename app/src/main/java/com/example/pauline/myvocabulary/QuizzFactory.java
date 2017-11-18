package com.example.pauline.myvocabulary;

/**
 * Created by pauline on 16/11/2017.
 */

public class QuizzFactory {
    public static Quizz getQuizz(String input, ListWord list) {
        switch (input) {
            case "QuizzWordTranslation":
                return new QuizzWordTranslation(list);
            case "QuizzTranslationWord":
                return new QuizzTranslationWord(list);
        }
        throw new IllegalArgumentException("This quizz doesn't exist!");
    }
}