package com.team3m.game.mafia;

import java.util.HashSet;
import java.util.Set;

public class VotingCandidates {
    private static Set<String> candidates = new HashSet<>();

    void setCandidate(String player_ID) {
        candidates.add(player_ID);
    }

    void clearCandidates() {
        candidates.clear();
    }

    public Set<String> getCandidates() {
        return candidates;
    }

}
