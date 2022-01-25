package com.overshade.blackfoxlauncher.Games.PegSolitaire;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.overshade.blackfoxlauncher.R;

/**
 * PEG SOLITAIRE GAME
 */
public class PegSolitaire extends Fragment {
    private View view;

    public PegSolitaire() {
        // Required empty public constructor
    }


    public static PegSolitaire newInstance() {
        PegSolitaire fragment = new PegSolitaire();
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
        view = inflater.inflate(R.layout.fragment_peg_solitaire, container, false);
        startGame();
        return view;
    }

    private void startGame() {
        PegSolitaireGame game = new PegSolitaireGame(view);
        game.restart();
    }

}