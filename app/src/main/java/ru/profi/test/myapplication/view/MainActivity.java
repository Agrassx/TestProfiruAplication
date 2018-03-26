package ru.profi.test.myapplication.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;

import ru.profi.test.myapplication.R;
import ru.profi.test.myapplication.view.FullImageView.FullImageViewFragment;
import ru.profi.test.myapplication.view.base.BaseFragment;
import ru.profi.test.myapplication.view.friends.FriendsFragment;
import ru.profi.test.myapplication.view.login.LoginFragment;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private boolean isResumed = false;
    private MainActivityView activityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityView = this;

        String[] fingerprint = VKUtil.getCertificateFingerprint(this, this.getPackageName());

//        for (String f : fingerprint) {
//            Log.e("finger", f);
//        }

        VKSdk.wakeUpSession(this, new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {
                if (isResumed) {
                    switch (res) {
                        case LoggedOut:
                            redirectTo(LoginFragment.newInstance(activityView));
                            break;
                        case LoggedIn:
                            break;
                        case Pending:
                            break;
                        case Unknown:
                            break;
                    }
                }
            }

            @Override
            public void onError(VKError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
        if (VKSdk.isLoggedIn()) {
            redirectTo(FriendsFragment.newInstance(activityView));
        } else {
            redirectTo(LoginFragment.newInstance(activityView));
        }
    }

    @Override
    protected void onPause() {
        isResumed = false;
        super.onPause();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // User passed Authorization
                redirectTo(FriendsFragment.newInstance(activityView));
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization
                redirectTo(LoginFragment.newInstance(activityView));
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void redirectTo(BaseFragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment, tag)
                    .commit();
        }
    }

    @Override
    public void redirectWithBackStackTo(BaseFragment fragment, View sharedElement, String name) {
        String tag = fragment.getClass().getSimpleName();

        if (tag.equals(FullImageViewFragment.class.getSimpleName())) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addSharedElement(sharedElement, name)
                    .replace(R.id.container, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
            return;
        }

        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
        }
    }
}


