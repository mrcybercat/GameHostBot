package com.teamMMM.bot.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.awt.*;

public class CommandPlaceholderEmbed {
    public static EmbedBuilder createPlaceholderEmbed(SlashCommandEvent event, String commandName) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getMember().getUser().getAsTag() + " (" + event.getMember().getId() + ")", null,
                event.getMember().getUser().getAvatarUrl());
        builder.setDescription("This is a" + commandName + "command placeholder");
        builder.addField("If you have any questions, - dont", "Really dont do that, I insist", false);
        builder.setColor(new Color(72, 238, 27, 104));
        builder.setTimestamp(event.getTimeCreated());
        return builder;
    }
}
