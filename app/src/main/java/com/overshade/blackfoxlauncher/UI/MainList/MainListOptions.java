package com.overshade.blackfoxlauncher.UI.MainList;


import androidx.fragment.app.Fragment;

import com.overshade.blackfoxlauncher.R;
import com.overshade.blackfoxlauncher.UI.GameList.GameList;

public class MainListOptions {
    private static final MainListOptions[] options = new MainListOptions[]{

                //[+--------OPTIONS--------+]

                new MainListOptions(
                        "Play Games",
                        "Enjoy our games and earn coins just for playing!",
                        R.drawable.ic_gamepad,
                        GameList.class
                ),

                new MainListOptions(
                        "Play Games",
                        "Enjoy our games and earn coins just for playing!",
                        R.drawable.ic_gamepad,
                        GameList.class
                ),

                new MainListOptions(
                        "Play Games",
                        "Enjoy our games and earn coins just for playing!",
                        R.drawable.ic_gamepad,
                        GameList.class
                )

                //[+--------END OF OPTIONS--------+]
    };
    public static MainListOptions[] getOptions() {return options;}


    //[+--------INNER CLASS--------+]

    private String name;
    private String description;
    private int iconResource;
    private Class<? extends Fragment> nextPage;

    private MainListOptions(String name, String description, int iconResource,
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
