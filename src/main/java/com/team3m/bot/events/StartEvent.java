package com.team3m.bot.events;


import com.team3m.bot.commands.inter_game.StartGameCmd;
import com.team3m.bot.commands.inter_lobby.*;
import com.team3m.bot.commands.inter_util.AboutCmd;
import com.team3m.bot.commands.inter_util.StatusCmd;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.util.List;
import java.util.Objects;

@Component
public class StartEvent extends ListenerAdapter {


    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        GamesManager gamesManager = GamesManager.getInstance();
        gamesManager.getGuildManagers().add(new GuildGamesManager(event.getGuild().getId()));

//        List<Command> commands = event.getGuild().retrieveCommands().complete();
//        for (Command command : commands) {
//            event.getGuild().deleteCommandById(command.getId()).queue();
//        }

        (new CreateGameCmd()).registerCommandData(event);
        (new DeleteGameCmd()).registerCommandData(event);
        (new EditGameSettingsCmd()).registerCommandData(event);
        (new JoinGameCmd()).registerCommandData(event);
        (new LeaveGameCmd()).registerCommandData(event);

        (new StartGameCmd()).registerCommandData(event);

        (new StatusCmd()).registerCommandData(event);
        (new AboutCmd()).registerCommandData(event);
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
