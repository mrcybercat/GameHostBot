package com.team3m.bot.util;

import java.awt.*;

public class ColorHandler{
    public enum StatusColorEnum {
        SUCCESS,
        ERROR,
        WARNING,
        NEUTRAL,
        VICTORY
    }

    public static Color getColor(StatusColorEnum colorEnum){
        Color color = null;
        switch (colorEnum) {
            case SUCCESS:
                color = new Color(72, 238, 27, 104);
                break;

            case ERROR:
                color = new Color(238, 27, 27, 158);
                break;

            case WARNING:
                color = new Color(255, 194, 0, 224);
                break;

            case NEUTRAL:
                color = new Color(110, 107, 115, 224);
                break;

            case VICTORY:
                color = new Color(148, 75, 255, 224);
                break;

            default:
                color = new Color(255, 255, 255, 255);
                break;
        }
        return color;
    }
}

