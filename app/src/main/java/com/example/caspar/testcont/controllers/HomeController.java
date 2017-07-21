package com.example.caspar.testcont.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.ChangeHandlerFrameLayout;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.example.caspar.testcont.R;
import com.example.caspar.testcont.controllers.base.BaseController;

import butterknife.BindView;

/**
 * Created by Caspar on 17.07.2017.
 */

public class HomeController extends BaseController {
    public static final String TAG = "HomeController";
    private Router childRouter;
    @BindView(R.id.home_navigation_controller_container)
    ChangeHandlerFrameLayout homeNavigationControllerContainer;

    public HomeController() {
    }

    public HomeController(Bundle args) {
        super(args);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_home,container,false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {

        super.onViewBound(view);
        getRouter().pushController(RouterTransaction.with(new PagerController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
        //childRouter.setRoot(RouterTransaction.with(new PagerController()));
    }
}
