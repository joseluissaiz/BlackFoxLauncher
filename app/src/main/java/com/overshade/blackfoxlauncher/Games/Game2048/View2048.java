package com.overshade.blackfoxlauncher.Games.Game2048;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.overshade.blackfoxlauncher.Games.PegSolitaire.PegSolitaireGame;
import com.overshade.blackfoxlauncher.R;

/**
 * 2048 GAME
 */
public class View2048 extends Fragment {
    private View view;

    public View2048() {
        // Required empty public constructor
    }


    public static View2048 newInstance() {
        View2048 fragment = new View2048();
        Bundle args = new Bundle();
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
        view = inflater.inflate(R.layout.fragment_2048, container, false);
        startGame();
        return view;
    }

    private void startGame() {
        Game2048 game = new Game2048(view);
        game.restart();
    }

}