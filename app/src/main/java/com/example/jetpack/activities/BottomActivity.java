package com.example.jetpack.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jetpack.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomActivity extends AppCompatActivity {
    BottomNavigationView navMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        navMenu=findViewById(R.id.bottomNavigationView);
        setUpNavBottom();

    }

    private void setUpNavBottom(){
        NavHostFragment hostFragment=(NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragment3);
        if (hostFragment!=null){
            NavController navController=hostFragment.getNavController();
            NavigationUI.setupWithNavController(navMenu,navController);//将menu与navController进行管理
        }
    }

}
