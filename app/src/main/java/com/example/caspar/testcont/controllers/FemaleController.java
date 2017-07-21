package com.example.caspar.testcont.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.caspar.testcont.R;
import com.example.caspar.testcont.controllers.base.BaseController;
import com.example.caspar.testcont.util.BundleBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Caspar on 19.07.2017.
 */

public class FemaleController extends BaseController {
    ImageView imageView4,imageView2,imageView3;


    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_female, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

    }

    public FemaleController() {
        this(new BundleBuilder(new Bundle()).build());

    }

    public FemaleController(Bundle args) {
        super(args);
    }

}
