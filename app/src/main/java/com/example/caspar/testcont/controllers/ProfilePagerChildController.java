package com.example.caspar.testcont.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caspar.testcont.R;
import com.example.caspar.testcont.controllers.base.BaseController;
import com.example.caspar.testcont.util.BundleBuilder;

import butterknife.BindView;

/**
 * Created by Caspar on 18.07.2017.
 */

public class ProfilePagerChildController extends BaseController {
    @BindView(R.id.tv_title)
    TextView textView;
    private static final String KEY_TITLE = "ProfilePagerChildController.title";
    public ProfilePagerChildController(String title) {
        this(new BundleBuilder(new Bundle()).putString(KEY_TITLE,title).build());
    }

    public ProfilePagerChildController(Bundle args) {
        super(args);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_profile_child,container,false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        textView.setText(getArgs().getString(KEY_TITLE));
    }
}
