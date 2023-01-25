package com.team3m.game.mafia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Roles {

    /**
     * This class stores the names of the roles and
     *  assigns randomly all players to a role and stores this information
     */

    public HashMap<String, String> playerRoles = new HashMap<>();

    protected void setPlayerRoles(Set<String> playerIDs, boolean clean) {
        // method fills in an empty map with inputting id's and roles

        if (clean = true) {
            playerRoles.clear();
        }

        ArrayList<String> list = new ArrayList<>(playerIDs);

        String[] arr = assignRoles(playerIDs.size());

        for (int i = 0; i < list.size(); i++) {
            playerRoles.put(list.get(i), arr[i]);
        }
    }

    private String[] assignRoles(int number) {

        String[] setOfRoles = new String[number];
        ArrayList<String> special = new ArrayList<>();
        special.add("Civilian");
        special.add("Civilian");
        special.add("Mafia");
        special.add("Commissar");


        if (number < 4) { // If the players <4 then throw out the message that the game does not make sense
        }

        if (number == 4) { // If there are 4 players, then 2 peaceful, 1 commissar and 1 mafia
            randomAssignment(setOfRoles, special);
        }


        if (number == 5) { // if there are 5 players, then 2 civilians, 1 commissar, 1 doctor and 1 mafia
            special.add("Doctor");

            randomAssignment(setOfRoles, special);
        }

        if (number == 6) { // if 6 players, then 3 peaceful, 1 commissar, 1 doctor and 1 mafia
            special.add("Civilian");
            special.add("Doctor");

            randomAssignment(setOfRoles, special);
        }

        if (number == 7) { // If there are 7 players, then 3 peaceful, 1 commissar, 1 doctor, and 2 mafia
            special.add("Civilian");
            special.add("Mafia");
            special.add("Doctor");

            randomAssignment(setOfRoles, special);
        }

        if (number == 8) { // if 8 players, then 3 peaceful, 1 commissar, 1 doctor, 1 whore and 2 mafia
            special.add("Civilian");
            special.add("Mafia");
            special.add("Doctor");
            special.add("Prostitute");

            randomAssignment(setOfRoles, special);
        }

        return setOfRoles;
    }

    private void randomAssignment(String[] setOfRoles, ArrayList<String> special) {
        while (special.size() != 0) {

            int randomNumberOfCell = 0;
            double numDouble = 0;

            for (int i = 0; i < setOfRoles.length; i++) {
                numDouble = Math.random() * special.size();
                randomNumberOfCell = (int) numDouble;

                setOfRoles[i] = special.get(randomNumberOfCell);
                special.remove(randomNumberOfCell);
            }
        }
    }

    public int getPlayerRolesSize() {
        return playerRoles.size();
    }

}


