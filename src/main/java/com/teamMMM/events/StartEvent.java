package com.teamMMM.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.awt.*;
@Component
public class StartEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith("?")) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor(event.getMember().getUser().getAsTag() + " (" + event.getMember().getId() + ")", null,
                    event.getMember().getUser().getAvatarUrl());
            builder.setDescription("Bot is kinda up & running, think!");
            builder.addField("If you have any questions, - dont", "Really dont do that, I insist", false);
            builder.setColor(new Color(72, 238, 27, 104));
            builder.setTimestamp(event.getMessage().getTimeCreated());
            event.getChannel().sendMessageEmbeds(builder.build()).queue();
        }
    }
}