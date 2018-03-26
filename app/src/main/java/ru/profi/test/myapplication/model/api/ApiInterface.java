package ru.profi.test.myapplication.model.api;

import com.vk.sdk.api.VKRequest;

public interface ApiInterface {

    void loadFriends(VKRequest.VKRequestListener listener);

}
