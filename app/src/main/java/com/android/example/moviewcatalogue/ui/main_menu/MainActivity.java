package com.android.example.moviewcatalogue.ui.main_menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.ui.main_menu.main_menu_fragment.FavoriteFragment;
import com.android.example.moviewcatalogue.ui.main_menu.main_menu_fragment.MovieFragment;
import com.android.example.moviewcatalogue.ui.main_menu.main_menu_fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String LOAD_FRAGMENT_MAIN_MENU = "load_fragment_main_menu";

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        setupClickListener();

        /*
         * We load the fragment based on wether it's is first open or from intent
         * */
        Intent intent = getIntent();
        int intentFragment = intent.getIntExtra(LOAD_FRAGMENT_MAIN_MENU, 1);
        if (intentFragment != -1) {
            loadFragmentFromIntent(intentFragment);
        } else {
            loadFragment(new TvShowFragment());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.menu_tv_show:
                fragment = new TvShowFragment();
                break;
            case R.id.menu_movie:
                fragment = new MovieFragment();
                break;
            case R.id.menu_favorite:
                fragment = new FavoriteFragment();
                break;
        }
        return loadFragment(fragment);
    }

    /*
     * Method to launch specific Fragment from Intent
     * */
    private void loadFragmentFromIntent(int intentFragment) {
        Fragment fragment = null;
        switch (intentFragment) {
            case 1:
                fragment = new TvShowFragment();
                break;
            case 2:
                fragment = new MovieFragment();
                break;
            case 3:
                fragment = new FavoriteFragment();
                break;
        }
        loadFragment(fragment);
    }

    // method untuk load fragment yang sesuai
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_content, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    /*
     * Setup click listener
     * */
    private void setupClickListener() {
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    /*
     * Initialize component
     * */
    private void initComponent() {
        // inisialisasi BottomNavigaionView
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }


}
