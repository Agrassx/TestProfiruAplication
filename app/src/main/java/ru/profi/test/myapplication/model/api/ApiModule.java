package ru.profi.test.myapplication.model.api;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;

public class ApiModule implements ApiInterface {

    private String url = "https://oauth.vk.com/token?grant_type=password&client_id=6410704&" +
            "client_secret=%s&username=%s&password=%s";

    public ApiModule() {

    }


    @Override
    public void loadFriends(VKRequest.VKRequestListener listener) {
        VKRequest request = VKApi.friends().get(VKParameters.from(
                VKApiConst.FIELDS,
                "id,first_name,last_name,photo_100,photo_max_orig")
        );
        request.executeWithListener(listener);
    }
}
