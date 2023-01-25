package com.team3m.game.mafia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Roles {
    public HashMap<String, String> playerRoles = new HashMap<>();

    protected void setPlayerRoles(Set<String> playerIDs, boolean clean) {

        if (clean == true) {
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


        if (number < 4) {
        }

        if (number == 4) {
            randomAssignment(setOfRoles, special);
        }


        if (number == 5) {
            special.add("Doctor");

            randomAssignment(setOfRoles, special);
        }

        if (number == 6) {
            special.add("Civilian");
            special.add("Doctor");

            randomAssignment(setOfRoles, special);
        }

        if (number == 7) {
            special.add("Civilian");
            special.add("Mafia");
            special.add("Doctor");

            randomAssignment(setOfRoles, special);
        }

        if (number == 8) {
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


