package com.example.caspar.testcont;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.example.caspar.testcont.controllers.BottomNavigationController;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends AppCompatActivity implements ActionBarProvider{

    @BindView(R.id.controller_container)
    ViewGroup container;

    private Router router;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //setSupportActionBar(toolbar);
        router = Conductor.attachRouter(this,container,savedInstanceState);
        if(!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new BottomNavigationController(R.menu.navigation)));
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_icons, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_help:
                addToBusketOpen();
                return true;
            default:
                return true;
        }
    }
    public void addToBusketOpen(){
        Intent intent = new Intent(MainActivity.this,BusketActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.hold, R.anim.slide_in_up);
        //overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }

    @Override
    public void onBackPressed() {
        if(!router.handleBack()) {
            super.onBackPressed();
        }
    }
}
