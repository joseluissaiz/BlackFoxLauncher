package com.overshade.blackfoxlauncher.UI;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.overshade.blackfoxlauncher.MainActivity;
import com.overshade.blackfoxlauncher.R;

public class SplashScreen extends Fragment {
    private View view;

    public SplashScreen() {
        // Required empty public constructor
    }


    public static SplashScreen newInstance() {
        SplashScreen fragment = new SplashScreen();
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
        view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        startSplashAnimation();
        return view;
    }

    private void startSplashAnimation() {
        ImageView imageLogo = view.findViewById(R.id.splash_logo);
        ImageView imageFx = view.findViewById(R.id.splash_fx);
        TextView versionTextView = view.findViewById(R.id.splash_version);

        //Version Animation
        AlphaAnimation versionAlpha = new AlphaAnimation(0.0f, 1.0f);
        versionAlpha.setDuration(1000);
        versionTextView.startAnimation(versionAlpha);

        //Rotation FX
        RotateAnimation fxRotation = new RotateAnimation(
                0f,
                10f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        //Translation FX
        TranslateAnimation fxTranslate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                -0.01f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0.01f
        );

        //AnimSet FX
        AnimationSet animSetFX = new AnimationSet(false);
        animSetFX.addAnimation(fxRotation);
        animSetFX.addAnimation(fxTranslate);
        animSetFX.setDuration(4000);
        animSetFX.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                launchMainMenu();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageFx.startAnimation(animSetFX);


        //Transition Drawable
        TransitionDrawable drawableLogo = (TransitionDrawable) imageLogo.getDrawable();
        drawableLogo.setCrossFadeEnabled(true);
        drawableLogo.startTransition(1000);

        //Scale animation
        ScaleAnimation scale = new ScaleAnimation(
                0.2f, 1f, 0.2f, 1f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        //Animation set to play together
        AnimationSet animSet = new AnimationSet(false);
        animSet.addAnimation(scale);
        animSet.setDuration(1000);
        animSet.setFillEnabled(true);
        animSet.setFillAfter(true);
        imageLogo.startAnimation(animSet);
    }

    private void launchMainMenu() {
        MainActivity activity = (MainActivity)requireActivity();
        activity.replaceFragment(R.id.fragment_container, MainMenu.class,
                FragmentTransitions.ENTER_HORIZONTAL);
    }
}