package com.overshade.blackfoxlauncher.UI.MainList;

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
import com.overshade.blackfoxlauncher.UI.GameList.GameList;

public class MainList extends Fragment {
    private View         view;
    private RecyclerView recyclerView;

    public MainList() {
        // Required empty public constructor
    }


    public static MainList newInstance() {
        MainList fragment = new MainList();
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
        view = inflater.inflate(R.layout.fragment_main_list, container, false);

        MainActivity activity = (MainActivity) requireActivity();
        activity.setCurrentMenu(MainList.class);

        recyclerView = view.findViewById(R.id.recyclerview);
        MainListAdapter adapter = new MainListAdapter(requireContext(), MainListOptions.getOptions());
        adapter.setClickListener((view, position) -> {
            switch (position) {
                //[+-----------OPTIONS LOGIC------------+]
                
                case 0: //Play Games
                    changeScreen(MainListOptions.getOptions()[0].getNextPage());
                    break;

                //[+--------END OF OPTIONS LOGIC--------+]

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void changeScreen(Class<? extends Fragment> nextPage) {
        MainActivity activity = (MainActivity) requireActivity();
        activity.replaceFragment(R.id.fragment_list_container, nextPage, FragmentTransitions.ENTER_HORIZONTAL);
    }

}