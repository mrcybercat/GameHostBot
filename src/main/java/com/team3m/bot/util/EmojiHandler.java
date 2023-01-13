package com.team3m.bot.util;


import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.Button;

public class EmojiHandler{
    public enum EmojiEnum {
        MAFIA,
        COUP,
        DEFAULT_GAME,

        CONFIRM,
        DISCARD,

        FEW_PLAYERS,
        ENOUGH_PLAYERS,
        MANY_PLAYERS,
        LOT_OF_PLAYERS
    }

    public static String getEmoji(EmojiEnum emojiEnum){
        String emoji = null;
        switch (emojiEnum) {
            case MAFIA:
                emoji = "U+1F575";
                break;

            case COUP:
                emoji = "U+1F5E1";
                break;

            case DEFAULT_GAME:
                emoji = "U+2699";
                break;

            case CONFIRM:
                emoji = "U+2714";
                break;

            case DISCARD:
                emoji = "U+274C";
                break;

            case FEW_PLAYERS:
                emoji = "U+26FA";
                break;

            case ENOUGH_PLAYERS:
                emoji = "U+1F3E0";
                break;

            case MANY_PLAYERS:
                emoji = "U+1F3D8";
                break;

            case LOT_OF_PLAYERS:
                emoji = "U+1F3D9";
                break;

            default:
                emoji = "U+2754";
                break;
        }
        return emoji;
    }
}

