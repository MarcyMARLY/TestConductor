package com.example.caspar.testcont.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caspar.testcont.R;
import com.example.caspar.testcont.controllers.base.BaseController;

/**
 * Created by Caspar on 19.07.2017.
 */

public class MaleController extends BaseController {
    public MaleController() {
    }

    public MaleController(Bundle args) {
        super(args);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_male,container,false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
    }
}
