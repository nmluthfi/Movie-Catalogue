package com.android.example.moviewcatalogue.ui.main_menu.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.preferences.MyPreferenceFragment;

public class UserPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preference);
        getSupportFragmentManager().beginTransaction().add(R.id.setting_holder, new MyPreferenceFragment()).commit();
    }
}
