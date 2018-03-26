package ru.profi.test.myapplication.model.api.response;

import java.util.List;

import ru.profi.test.myapplication.model.classes.Friend;

public class FriendsResponse {

    private int count;
    private List<Friend> items;

    public List<Friend> getList() {
        return items;
    }

    public int getCount() {
        return count;
    }

}
