package com.overshade.blackfoxlauncher;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import com.overshade.blackfoxlauncher.UI.FragmentTransitions;
import com.overshade.blackfoxlauncher.UI.GameList.GameList;
import com.overshade.blackfoxlauncher.UI.MainList.MainList;
import com.overshade.blackfoxlauncher.UI.MainMenu;
import com.overshade.blackfoxlauncher.UI.SplashScreen;

public class MainActivity extends AppCompatActivity {
    private static int entries = 0;
    private Class<? extends Fragment> currentMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (entries == 0) {
            replaceFragment(R.id.fragment_container, SplashScreen.class, FragmentTransitions.NONE);
            entries++;
        } else {
            replaceFragment(R.id.fragment_container, MainMenu.class,
                    FragmentTransitions.ENTER_HORIZONTAL);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideUI();
    }

    public void replaceFragment(int containerRes, Class<? extends Fragment> fragmentClass,
                                 FragmentTransitions transition) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (transition) {
            case ENTER_HORIZONTAL:
                transaction.setCustomAnimations(R.anim.enter_right, R.anim.slide_left);
                break;
            case EXIT_HORIZONTAL:
                transaction.setCustomAnimations(R.anim.enter_left, R.anim.slide_right);
                break;
            case NONE:
                break;
        }
        transaction.replace(containerRes, fragmentClass, null);
        transaction.commit();
    }

    //////// Handling menu interactions
    @Override
    public void onBackPressed() {
        if (currentMenu == MainList.class) {
            //System.exit(0);
        }
        if (currentMenu == GameList.class) {
            replaceFragment(R.id.fragment_list_container, MainList.class, FragmentTransitions.EXIT_HORIZONTAL);
        }

    }

    private void hideUI() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    public void setCurrentMenu(Class<? extends Fragment> currentMenu) {
        this.currentMenu = currentMenu;
    }
}