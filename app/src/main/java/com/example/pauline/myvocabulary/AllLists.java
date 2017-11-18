package com.example.pauline.myvocabulary;

import java.util.ArrayList;
/**
 * Created by pauline on 06/11/2017.
 */

/**
 *  This class contains all the lists of words
 */
public class AllLists {

    public ArrayList<ListWord> lists;

    public AllLists() {
        this.lists = new ArrayList<>();
    }

    public void addList(ListWord list) {
        this.lists.add(list);
    }

    public int countLists() {
        return lists.size();
    }

    public void displayAllLists() {
        for (ListWord listW: lists) {
            System.out.println(listW.getName());
        }
    }

    /**
     * Allow to find a list with the name
     * @param name
     * @return the list of word found
     */
    public ListWord findAList(String name) {
        for (ListWord list: lists) {
            if (list.getName().equals(name)) {
                return list;
            }
        }
        return null;
    }

    public boolean findList(String name) {
        if (findAList(name) == null) {
            return false;
        } else {
            return true;
        }
    }
}
