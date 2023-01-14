package com.team3m.bot.util;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.Button;

public class ConformationButtonHandler {

    public Button[] createConformationButtons(String confirmLabel, String discardLabel){
        return new Button[] {
            Button.success(confirmLabel, Emoji.fromUnicode(EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.CONFIRM))),
            Button.secondary(discardLabel, Emoji.fromUnicode(EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.DISCARD)))
        };
    }


}
