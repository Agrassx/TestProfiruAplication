package ru.profi.test.myapplication.view.FullImageView;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.model.VKPhotoArray;

import ru.profi.test.myapplication.R;
import ru.profi.test.myapplication.model.classes.Friend;
import ru.profi.test.myapplication.presenter.FullImageViewPresenter;
import ru.profi.test.myapplication.view.MainActivityView;
import ru.profi.test.myapplication.view.base.BaseFragment;
import ru.profi.test.myapplication.view.base.FragmentView;

public class FullImageViewFragment extends BaseFragment implements FullImageView {

    private String photoURL;
    private ImageView imageView;
    private TextView textViewLoadPercent;
    private FullImageViewPresenter presenter;
    private MainActivityView activityView;
    private Friend user;


    public static FullImageViewFragment newInstance(MainActivityView activityView, Friend user) {
        Bundle args = new Bundle();
        FullImageViewFragment fragment = new FullImageViewFragment();
        fragment.setUser(user);
        fragment.setActivityView(activityView);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_image_view, container, false);
        imageView = view.findViewById(R.id.imageViewFullPhoto);
        textViewLoadPercent = view.findViewById(R.id.textViewLoadPercent);
        if (savedInstanceState == null) {
            presenter.loadPhoto(user.getPhoto_max_orig(), imageView, textViewLoadPercent);
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new FullImageViewPresenter(this);
        VKRequest requestPhoto = new VKRequest("photos.get", VKParameters.from(VKApiConst.OWNER_ID,
                user.getId(), VKApiConst.ALBUM_ID, "profile"), VKRequest.HttpMethod.GET, VKPhotoArray.class);
    }

    public void setUser(Friend user) {
        this.user = user;
    }

    @Override
    public FragmentView getIView() {
        return this;
    }

    @Override
    public void setActivityView(MainActivityView activityView) {
        this.activityView = activityView;
    }

    @Override
    public void showMessage(String message) {

    }

    public Friend getUser() {
        return user;
    }

    private void setPhotoUrl(String url) {
        this.photoURL = url;
    }
}
