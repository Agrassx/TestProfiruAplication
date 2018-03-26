package ru.profi.test.myapplication.view.friends;

import java.util.List;

import ru.profi.test.myapplication.model.classes.Friend;
import ru.profi.test.myapplication.view.base.FragmentView;

public interface FriendsView extends FragmentView {

    void showFriends(List<Friend> friendList);

}
