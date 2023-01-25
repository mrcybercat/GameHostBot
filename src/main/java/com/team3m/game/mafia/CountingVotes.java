package com.team3m.game.mafia;

import java.util.ArrayList;

public class CountingVotes extends Votes {

    /**
     * this class is responsible for counting votes
     * responsible for re-voting
     */

    CountingVotes(String target_id, String voter_id) {
        super(target_id, voter_id);
    }


    String countVotes(ArrayList<Votes> voteStorage) {

        //count the votes
        // the one who has more loses the corresponding id is returned for the kill

        /*
        ArrayList <String> targetIds= new ArrayList<>();

        for (int i = 0; i < voteStorage.size(); i++) {
            targetIds.add(voteStorage.get(i).getTarget_id()) ;
        }

        Map<String, Integer> frequency = targetIds.stream()
                // собираем элементы листа в карту
                .collect(Collectors.toMap(
                        // ключ - строка
                        e -> e,
                        // значение - число,
                        // количество вхождений
                        e -> 1,
                        // суммируем количество вхождений
                        Integer::sum));
        int x=0;
        int positionInTargetIds=0;
        for (int i = 0; i < targetIds.size(); i++) {
            if (frequency.get(targetIds.get(i))>x)

             x=frequency.get(targetIds.get(i));
            positionInTargetIds=i;

        }*/


        return null;
    }
}
