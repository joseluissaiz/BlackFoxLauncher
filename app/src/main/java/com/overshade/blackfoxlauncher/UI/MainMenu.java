package com.overshade.blackfoxlauncher.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.overshade.blackfoxlauncher.MainActivity;
import com.overshade.blackfoxlauncher.R;
import com.overshade.blackfoxlauncher.UI.MainList.MainList;

public class MainMenu extends Fragment {
    private View view;

    public MainMenu() {
        // Required empty public constructor
    }


    public static MainMenu newInstance() {
        MainMenu fragment = new MainMenu();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        startBackgroundFX();
        loadMainList();
        return view;
    }


    private void loadMainList() {
        MainActivity activity = (MainActivity) requireActivity();
        activity.replaceFragment(R.id.fragment_list_container, MainList.class,
                FragmentTransitions.ENTER_HORIZONTAL);
    }

    private void startBackgroundFX() {
        ImageView background = view.findViewById(R.id.background);
        //Rotation FX
        RotateAnimation fxRotation = new RotateAnimation(
                0f,
                360f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        //AnimSet FX
        AnimationSet animSetFX = new AnimationSet(false);
        animSetFX.addAnimation(fxRotation);
        animSetFX.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                background.startAnimation(animSetFX);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animSetFX.setDuration(90000);
        background.startAnimation(animSetFX);
    }

}