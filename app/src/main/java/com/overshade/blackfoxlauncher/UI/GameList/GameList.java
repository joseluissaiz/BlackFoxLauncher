package com.overshade.blackfoxlauncher.UI.GameList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.overshade.blackfoxlauncher.MainActivity;
import com.overshade.blackfoxlauncher.R;
import com.overshade.blackfoxlauncher.UI.FragmentTransitions;
import com.overshade.blackfoxlauncher.UI.MainList.MainList;
import com.overshade.blackfoxlauncher.UI.MainList.MainListAdapter;
import com.overshade.blackfoxlauncher.UI.MainList.MainListOptions;

public class GameList extends Fragment {

    private MainActivity activity;
    private View         view;
    private RecyclerView recyclerView;

    public GameList() {
        // Required empty public constructor
    }


    public static GameList newInstance() {
        GameList fragment = new GameList();
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
        // Inflate the layout for this fragment and its components
        view = inflater.inflate(R.layout.fragment_game_list, container, false);

        activity = (MainActivity) requireActivity();
        activity.setCurrentMenu(GameList.class);

        //on back arrow pressed
        view.findViewById(R.id.game_list_back).setOnClickListener(v -> activity.replaceFragment(
                R.id.fragment_list_container, MainList.class, FragmentTransitions.EXIT_HORIZONTAL
        ));

        recyclerView = view.findViewById(R.id.recyclerview);
        GameListAdapter adapter = new GameListAdapter(requireContext(), GameListOptions.getOptions());
        adapter.setClickListener((view, position) -> {
            switch (position) {
                //[+-----------OPTIONS LOGIC------------+]

                case 0: //2048
                    startGame(GameListOptions.getOptions()[0].getNextPage());
                    break;

                case 1: //Peg Solitaire
                    startGame(GameListOptions.getOptions()[1].getNextPage());
                    break;

                //[+--------END OF OPTIONS LOGIC--------+]

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }



    private void startGame(Class<? extends Fragment> game) {
        MainActivity activity = (MainActivity) requireActivity();
        activity.replaceFragment(R.id.fragment_list_container, game, FragmentTransitions.ENTER_HORIZONTAL);
    }

}