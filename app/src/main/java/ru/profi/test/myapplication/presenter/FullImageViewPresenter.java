package ru.profi.test.myapplication.presenter;

import android.widget.ImageView;
import android.widget.TextView;

import ru.profi.test.myapplication.model.asynctasks.ImageLoaderTask;
import ru.profi.test.myapplication.presenter.base.BasePresenter;
import ru.profi.test.myapplication.view.FullImageView.FullImageView;

public class FullImageViewPresenter extends BasePresenter {

    private FullImageView view;

    public FullImageViewPresenter(FullImageView view) {
        this.view = view;
    }

    public void loadPhoto(String url, ImageView view, TextView textView) {
        new ImageLoaderTask(view, textView).execute(url);
    }

}
