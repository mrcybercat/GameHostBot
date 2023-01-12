package com.team3m.bot.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;

import java.awt.*;

public class CommandEmbedBuilder {

    public static MessageEmbed createPlaceholderEmbed(GenericInteractionCreateEvent event, String name, String desc, ColorHandler.StatusColorEnum status) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getMember().getUser().getAsTag() + " (" + event.getMember().getId() + ")", null,
                event.getMember().getUser().getAvatarUrl());
        builder.addField(name, desc, false);
        builder.setColor(ColorHandler.getColor(status));
        return builder.build();
    }
}


//builder.setDescription("This is a" + commandName + "command placeholder");
//builder.setTimestamp(event.getTimeCreated());
