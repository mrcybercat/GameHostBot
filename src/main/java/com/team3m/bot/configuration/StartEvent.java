package com.team3m.bot.configuration;


import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.controllers.game.StartCommandController;
import com.team3m.bot.commands.controllers.game.StopCommandController;
import com.team3m.bot.commands.controllers.general.AboutCommandController;
import com.team3m.bot.commands.controllers.general.StatusCommandController;
import com.team3m.bot.commands.controllers.lobby.*;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.util.Objects;

@Component
public class StartEvent extends Command {


    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        GamesManager gamesManager = GamesManager.getInstance();
        gamesManager.getGuildManagers().add(new GuildGamesManager(event.getGuild().getId()));

//        List<Command> commands = event.getGuild().retrieveCommands().complete();
//        for (Command command : commands) {
//            event.getGuild().deleteCommandById(command.getId()).queue();
//        }

        (new CreateCommandController()).registerCommandData(event);
        (new DeleteCommandController()).registerCommandData(event);
        (new EditCommandController()).registerCommandData(event);
        (new JoinCommandController()).registerCommandData(event);
        (new LeaveCommandController()).registerCommandData(event);

        (new StartCommandController()).registerCommandData(event);
        (new StopCommandController()).registerCommandData(event);

        (new StatusCommandController()).registerCommandData(event);
        (new AboutCommandController()).registerCommandData(event);
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
