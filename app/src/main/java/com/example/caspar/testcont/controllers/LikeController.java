package com.example.caspar.testcont.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caspar.testcont.R;
import com.example.caspar.testcont.controllers.base.BaseController;

import org.w3c.dom.Text;

import butterknife.BindView;

/**
 * Created by Caspar on 17.07.2017.
 */

public class LikeController extends BaseController {
    public static final String TAG = "LikeController";
    @BindView(R.id.textView)
    TextView textView;

    public LikeController() {
    }

    public LikeController(Bundle args) {
        super(args);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_like,container,false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
    }
}
