package com.team3m.game.mafia;

import java.util.ArrayList;

public class Votes {
    private final String voter_id;
    private String target_id;

    ArrayList<Votes> voteStorage = new ArrayList<>();

    Votes(String target_id, String voter_id) {
        this.target_id = target_id;
        this.voter_id = voter_id;
    }

    void vote(String target_id, String voter_id) {
        voteStorage.add(new Votes(target_id, voter_id));
    }

    void revote(String newTarget_id, String voter_id) {// search for the voter ID on the arrayList and change his vote to a new one
        for (Votes votes : voteStorage) {
            if (votes.voter_id.equals(voter_id)) {
                votes.target_id = newTarget_id;
            }

        }
    }

    void cleanVoteStorage() {
        voteStorage.clear();
    }
}

