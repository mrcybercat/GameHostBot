package com.teamMMM.bot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.awt.*;
@Component
public class StatusEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot() && event.getMessage().getContentRaw().equals("status")) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor(event.getMember().getUser().getAsTag() + " (" + event.getMember().getId() + ")", null,
                    event.getMember().getUser().getAvatarUrl());
            builder.setDescription("I am glad to inform you, dear " + event.getMember().getUser().getName()  + ", that I am online and ready to resist");
            builder.addField("If you have any questions, - dont", "Really dont do that, I insist", false);
            builder.setColor(new Color(72, 238, 27, 104));
            builder.setTimestamp(event.getMessage().getTimeCreated());
            event.getChannel().sendMessageEmbeds(builder.build()).queue();
        }
    }

}