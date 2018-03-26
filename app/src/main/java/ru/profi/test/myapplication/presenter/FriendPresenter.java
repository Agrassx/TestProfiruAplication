package ru.profi.test.myapplication.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;

import ru.profi.test.myapplication.model.api.ApiInterface;
import ru.profi.test.myapplication.model.api.response.FriendsResponse;
import ru.profi.test.myapplication.presenter.base.BasePresenter;
import ru.profi.test.myapplication.view.friends.FriendsView;


public class FriendPresenter extends BasePresenter {
    private FriendsView view;


    public FriendPresenter(FriendsView view) {
        this.view = view;
    }


    public void loadFriends() {
        apiInterface.loadFriends(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                FriendsResponse friendsResponse;
                try {
                    friendsResponse = new Gson().fromJson(
                            response.json.getJSONObject("response").toString(),
                            FriendsResponse.class
                    );
                    view.showFriends(friendsResponse.getList());
                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        });
    }
}
