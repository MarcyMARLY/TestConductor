package com.example.caspar.testcont.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.support.RouterPagerAdapter;
import com.example.caspar.testcont.R;
import com.example.caspar.testcont.controllers.base.BaseController;

import java.util.Locale;

import butterknife.BindView;

/**
 * Created by Caspar on 18.07.2017.
 */

public class ProfilePagerController extends BaseController {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.mScroll_view)
    ScrollView mScrollView;
    public final RouterPagerAdapter pagerAdapter;

    public ProfilePagerController() {
        pagerAdapter = new RouterPagerAdapter(this) {
            @Override
            public void configureRouter(@NonNull Router router, int position) {
                if(!router.hasRootController())
                {
                    Controller page = new ProfilePagerChildController(String.format(Locale.getDefault(), "Child #%d (Swipe to see more)", position));
                    router.setRoot(RouterTransaction.with(page));
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if(position==0){
                    return "In process";
                }else{
                    return "Done";
                }
            }
        };

    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_profile_page,container,false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    protected void onDestroyView(@NonNull View view) {
        if (!getActivity().isChangingConfigurations()) {
            viewPager.setAdapter(null);
        }
        tabLayout.setupWithViewPager(null);
        super.onDestroyView(view);
    }

  /*  @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("ARTICLE_SCROLL_POSITION",
                new int[]{ mScrollView.getScrollX(), mScrollView.getScrollY()});

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final int[] position = savedInstanceState.getIntArray("ARTICLE_SCROLL_POSITION");
        if(position != null)
            mScrollView.post(new Runnable() {
                public void run() {
                    mScrollView.scrollTo(position[0], position[1]);
                }
            });
    }*/

    @Override
    protected String getTitle() {
        return "ViewPager Demo";
    }
}
