package com.example.caspar.testcont.controllers;

import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.transition.ChangeBounds;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bluelinelabs.conductor.ChangeHandlerFrameLayout;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.example.caspar.testcont.R;
import com.example.caspar.testcont.controllers.base.BaseController;
import com.example.caspar.testcont.controllers.ProfileController;
import com.example.caspar.testcont.util.BundleBuilder;

import butterknife.BindView;

/**
 * Created by Caspar on 17.07.2017.
 */

public class BottomNavigationController extends BaseController {

    public static final String TAG = "BottomNavigationController";

    private static final String KEY_MENU_RESOURCE = "key_menu_resource";
    private static final String KEY_STATE_ROUTER_BUNDLES = "key_state_router_bundles";
    private static final String KEY_STATE_CURRENTLY_SELECTED_ID = "key_state_currently_selected_id";

    static PagerController pagerController;
    static ProfilePagerController profilePagerController;
    @BindView(R.id.bottom_navigation_controller_container)
    ChangeHandlerFrameLayout controllerContainer ;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.bottom_navigation_root)
    LinearLayout bottomNavigationRoot;

    private int currentlySelectedItem;
    private SparseArray<Bundle> routerBundles;
    private Router childRouter;

    public BottomNavigationController(@MenuRes int menu) {
        this(new BundleBuilder(new Bundle()).putInt(KEY_MENU_RESOURCE,menu).build());
    }

    public BottomNavigationController(Bundle args) {
        super(args);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_bottom_navigation,container,false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        profilePagerController = new ProfilePagerController();
        pagerController = new PagerController();
        bottomNavigationView.inflateMenu(getMenuResource());
        Menu menu  = bottomNavigationView.getMenu();
        int menuSize = menu.size();
        childRouter = getChildRouter(controllerContainer);

        if(routerBundles == null){

            routerBundles = new SparseArray<>(menuSize);
            for(int  i=0 ; i<menuSize;i++){
                MenuItem menuItem = menu.getItem(i);
                int itemId= menuItem.getItemId();
                if(menuItem.isChecked()) {
                    childRouter.setRoot(RouterTransaction.with(BottomNavigationController.getControllerFor(
                            itemId
                    )));
                    bottomNavigationView.setSelectedItemId(itemId);
                    currentlySelectedItem = bottomNavigationView.getSelectedItemId();
                    break;
                }

            }
        }else {
            childRouter.rebindIfNeeded();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(currentlySelectedItem != item.getItemId()){
                    saveChildRouter(currentlySelectedItem);
                    clearChildRouter();

                    currentlySelectedItem = item.getItemId();
                    Bundle routerBundle = routerBundles.get(currentlySelectedItem);
                    if(routerBundle !=null && !routerBundle.isEmpty())
                    {
                        childRouter.restoreInstanceState(routerBundle);
                        childRouter.rebindIfNeeded();
                    }else {
                        childRouter.setRoot(RouterTransaction.with(BottomNavigationController.getControllerFor(
                                currentlySelectedItem
                                )));
                    }
                    return true;
                }else {
                    return false;
                }
            }
        });
    }
    private int getMenuResource() {
        return getArgs().getInt(KEY_MENU_RESOURCE);
    }
    private void saveChildRouter(int itemId){
        Bundle routerBundle = new Bundle();
        childRouter.saveInstanceState(routerBundle);
        routerBundles.put(itemId,routerBundle);
    }
    private void clearChildRouter(){
        childRouter.setPopsLastView(true);
        childRouter.popToRoot();
        childRouter.popCurrentController();
        childRouter.setPopsLastView(false);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        routerBundles = savedInstanceState.getSparseParcelableArray(KEY_STATE_ROUTER_BUNDLES);
        currentlySelectedItem = savedInstanceState.getInt(KEY_STATE_CURRENTLY_SELECTED_ID);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        saveChildRouter(currentlySelectedItem);
        outState.putSparseParcelableArray(KEY_STATE_ROUTER_BUNDLES,routerBundles);
        outState.putInt(KEY_STATE_CURRENTLY_SELECTED_ID,currentlySelectedItem);

    }

    @Override
    public boolean handleBack() {
        return childRouter.handleBack();
    }

    private static Controller getControllerFor(int menuItemId){
        Controller controller;
        switch(menuItemId) {
            case R.id.navigation_home:
                controller = new PagerController();
                break;
            case R.id.navigation_like:
                controller = new LikeController();
                break;
            case R.id.navigation_profile:
                controller = new ProfilePagerController();
                break;
            default:
                throw new IllegalStateException(
                        "Unknown bottomNavigationView item selected.");
        }
        return controller;
    }
}