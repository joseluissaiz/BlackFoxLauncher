package com.overshade.blackfoxlauncher.UI.GameList;

import androidx.fragment.app.Fragment;

import com.overshade.blackfoxlauncher.Games.Game2048.Game2048;
import com.overshade.blackfoxlauncher.Games.Game2048.View2048;
import com.overshade.blackfoxlauncher.Games.PegSolitaire.PegSolitaire;
import com.overshade.blackfoxlauncher.R;

public class GameListOptions {
    private static final GameListOptions[] options = new GameListOptions[]{

            //[+--------OPTIONS--------+]

            new GameListOptions(
                    "2048",
                    "Let's check your ability with sudokus and puzzles with this game!",
                    R.drawable.ic_2048_icon,
                    View2048.class
            ),

            new GameListOptions(
                    "Peg Solitaire",
                    "Do you like get frustrated, so this is a good game for you :)",
                    R.drawable.ic_peg_solitaire_icon,
                    PegSolitaire.class
            ),

            //[+--------END OF OPTIONS--------+]
    };

    public static GameListOptions[] getOptions() {
        return options;
    }


    //[+--------INNER CLASS--------+]

    private String name;
    private String description;
    private int iconResource;
    private Class<? extends Fragment> nextPage;

    private GameListOptions(String name, String description, int iconResource,
                            Class<? extends Fragment> nextPage) {
        this.name = name;
        this.description = description;
        this.iconResource = iconResource;
        this.nextPage = nextPage;
    }


    //[+--------GETTERS--------+]

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getIconResource() {
        return iconResource;
    }

    public Class<? extends Fragment> getNextPage() {
        return nextPage;
    }
}
