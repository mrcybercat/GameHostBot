package com.team3m.bot.util;


import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.Button;

public class EmojiHandler{
    public enum EmojiEnum {
        MAFIA,
        COUP,
        DEFAULT_GAME,
        CONFIRM,
        DISCARD
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

            default:
                emoji = "U+2754";
                break;
        }
        return emoji;
    }
}

