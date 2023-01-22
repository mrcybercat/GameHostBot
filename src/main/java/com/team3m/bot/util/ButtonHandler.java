package com.team3m.bot.util;

import com.team3m.bot.commands.abstracts.Command;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.Button;

public class ButtonHandler {

    public enum ButtonEnum {
        PRIMARY,
        SECONDARY,
        SUCCESS,
        DANGER
    }


    public static Button createButton(Command command, String label, EmojiHandler.EmojiEnum emoji, ButtonEnum button){
        switch (button){
            case PRIMARY:
                return Button.primary(command.getName() +"_button_" + label, Emoji.fromUnicode(EmojiHandler.getEmoji(emoji)));
            case SECONDARY:
                return Button.secondary(command.getName() +"_button_" + label, Emoji.fromUnicode(EmojiHandler.getEmoji(emoji)));
            case SUCCESS:
                return Button.success(command.getName() +"_button_" + label, Emoji.fromUnicode(EmojiHandler.getEmoji(emoji)));
            case DANGER:
                return Button.danger(command.getName() +"_button_" + label, Emoji.fromUnicode(EmojiHandler.getEmoji(emoji)));
        }
        return Button.secondary(command.getName() +"_button_" + label, Emoji.fromUnicode(EmojiHandler.getEmoji(emoji)));
    }

    public Button[] createConformationButtons(String confirmLabel, String discardLabel){
        return new Button[] {
            Button.success(confirmLabel, Emoji.fromUnicode(EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.CONFIRM))),
            Button.secondary(discardLabel, Emoji.fromUnicode(EmojiHandler.getEmoji(EmojiHandler.EmojiEnum.DISCARD)))
        };
    }


}
