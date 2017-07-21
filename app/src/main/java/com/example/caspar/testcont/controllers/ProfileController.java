package com.example.caspar.testcont.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.example.caspar.testcont.R;
import com.example.caspar.testcont.controllers.base.BaseController;

import butterknife.BindView;

/**
 * Created by Caspar on 18.07.2017.
 */

public class ProfileController extends BaseController {
    public ProfileController() {
    }

    public ProfileController(Bundle args) {
        super(args);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_profile,container,false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {

        super.onViewBound(view);
        getRouter().pushController(RouterTransaction.with(new ProfilePagerController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
    }
}
