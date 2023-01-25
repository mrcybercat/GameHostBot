package com.team3m.game.mafia;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Roles {
    private final HashMap<String, String> playerRoles = new HashMap<>();

    enum roles {
        CIVILIAN,
        MAFIA,
        COMMISSAR,
        DOCTOR,
        PROSTITUTE

    }

    public HashMap<String, String> getPlayerRoles() {
        return playerRoles;
    }

    protected void setPlayerRoles(Set<String> playerIDs) {

        ArrayList<String> list = new ArrayList<>(playerIDs);

        String[] arr = assignRoles(playerIDs.size());

        for (int i = 0; i < list.size(); i++) {
            playerRoles.put(list.get(i), arr[i]);
        }
    }

    private String[] assignRoles(int number) {

        String[] setOfRoles = new String[number];
        ArrayList<String> special = new ArrayList<>();
        special.add(roles.CIVILIAN.name());
        special.add(roles.CIVILIAN.name());
        special.add(roles.MAFIA.name());
        special.add(roles.COMMISSAR.name());

        if (number == 4) {
            randomAssignment(setOfRoles, special);
        }

        if (number == 5) {
            special.add(roles.DOCTOR.name());

            randomAssignment(setOfRoles, special);
        }

        if (number == 6) {
            special.add(roles.CIVILIAN.name());
            special.add(roles.DOCTOR.name());

            randomAssignment(setOfRoles, special);
        }

        if (number == 7) {
            special.add(roles.CIVILIAN.name());
            special.add(roles.MAFIA.name());
            special.add(roles.DOCTOR.name());

            randomAssignment(setOfRoles, special);
        }

        if (number == 8) {
            special.add(roles.CIVILIAN.name());
            special.add(roles.MAFIA.name());
            special.add(roles.DOCTOR.name());
            special.add(roles.PROSTITUTE.name());

            randomAssignment(setOfRoles, special);
        }

        return setOfRoles;
    }

    private void randomAssignment(String[] setOfRoles, ArrayList<String> special) {
        while (special.size() != 0) {

            int randomNumberOfCell = 0;
            double numDouble = 0;

            for (int i = 0; i < setOfRoles.length; i++) {
                SecureRandom rand = new SecureRandom();

                numDouble = rand.nextDouble(special.size());
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


