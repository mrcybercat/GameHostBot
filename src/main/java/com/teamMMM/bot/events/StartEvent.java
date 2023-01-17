package com.teamMMM.bot.events;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Objects;

@Component
public class StartEvent extends ListenerAdapter {


    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        event.getGuild().upsertCommand("start", "this is a start command").queue();
        event.getGuild().upsertCommand("join", "this is a join command").queue();
        event.getGuild().upsertCommand("edit", "this is a edit command").queue();
    }

    @Override
    public void onReady(ReadyEvent event)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getJDA().getSelfUser().getAsTag() + " (" + event.getJDA().getSelfUser().getId() + ")", null,
                event.getJDA().getSelfUser().getAvatarUrl());
        builder.setDescription("Bot is kinda up & running, think!");
        builder.addField("If you have any questions, - dont", "Really dont do that, I insist", false);
        builder.setColor(new Color(72, 238, 27, 104));
        builder.setTimestamp(event.getJDA().getSelfUser().getTimeCreated());
        for(Guild guild : event.getJDA().getGuilds()){
            TextChannel textChannel = guild.getDefaultChannel();
            Objects.requireNonNull(textChannel).sendMessageEmbeds(builder.build()).queue();
        }
    }
}
