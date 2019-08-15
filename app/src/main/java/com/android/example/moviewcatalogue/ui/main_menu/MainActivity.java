package com.android.example.moviewcatalogue.ui.main_menu;

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

    private static final String KEY_TITLE = "key_title";
    private static final String KEY_FRAGMENT = "key fragment";

    private BottomNavigationView bottomNavigationView;
    private Fragment pageContent = new TvShowFragment();
    private String title = "TV Show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        setupClickListener();


        if (savedInstanceState == null) {
            loadFragment(pageContent);
        } else {
            pageContent = getSupportFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT);
            title = savedInstanceState.getString(KEY_TITLE);
            loadFragment(pageContent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_tv_show:
                pageContent = new TvShowFragment();
                title = "TV Show";
                break;
            case R.id.menu_movie:
                pageContent = new MovieFragment();
                title = "Movie";
                break;
            case R.id.menu_favorite:
                pageContent = new FavoriteFragment();
                title = "Favorite";
                break;
        }
        return loadFragment(pageContent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_TITLE, title);
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT, pageContent);
        super.onSaveInstanceState(outState);
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

    private void setupClickListener() {
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void initComponent() {
        // inisialisasi BottomNavigaionView
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }


}
