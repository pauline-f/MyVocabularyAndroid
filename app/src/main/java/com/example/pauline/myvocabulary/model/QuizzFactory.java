package com.example.pauline.myvocabulary.model;

public class QuizzFactory {
    public static Quizz getQuizz(String input, ListWord list) {
        switch (input) {
            case "Word - Translation":
                return new QuizzWordTranslation(list);
            case "Translation - Word":
                return new QuizzTranslationWord(list);
        }
        throw new IllegalArgumentException("This quizz doesn't exist!");
    }
}