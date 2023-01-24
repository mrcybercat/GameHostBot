package com.team3m.bot.commands.services.lobby;

import com.team3m.bot.commands.controllers.lobby.CreateCommandController;
import com.team3m.bot.util.ButtonHandler;
import com.team3m.bot.util.ColorHandler;
import com.team3m.bot.util.CommandEmbedHandler;
import com.team3m.bot.util.EmojiHandler;
import com.team3m.game.abstracts.Lobby;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.stereotype.Service;

@Service
public class CreateCommandService {

    public void createLobby(SlashCommandEvent event) {
        GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());

        if(guildManager.getLobbies() != null  && guildManager.getLobbies().size() >= 25){
            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Ops!", "There no more lobbies available sorry", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
            return;
        }

        if(guildManager.ownerDuplicationCheck(guildManager.getLobbies(), event.getUser().getId())){
            event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Hey!", "You cant create multiple lobbies you sneaky", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
            return;
        }

        event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Please confirm your chose\nLobby name",  event.getOption("name").getAsString(), ColorHandler.StatusColorEnum.SUCCESS))
            .addActionRow(
                ButtonHandler.createButton(new CreateCommandController(), "confirm", EmojiHandler.EmojiEnum.CONFIRM, ButtonHandler.ButtonEnum.SUCCESS),
                ButtonHandler.createButton(new CreateCommandController(), "discard", EmojiHandler.EmojiEnum.DISCARD, ButtonHandler.ButtonEnum.SECONDARY))
            .setEphemeral(true).queue();
    }

    public void confirmCreation(ButtonClickEvent event) {
        String option = event.getComponentId().split("_")[2];
        switch (option){
            case "confirm":
                GuildGamesManager guildManager = GamesManager.getInstance().getGuildManager(event.getGuild().getId());
                if(guildManager.ownerDuplicationCheck(guildManager.getLobbies(), event.getUser().getId())){
                    event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Hey!", "You cant create multiple lobbies you sneaky", ColorHandler.StatusColorEnum.WARNING)).setEphemeral(true).queue();
                    return;
                }
                guildManager.getLobbies().add(new Lobby(event.getUser().getId(), event.getMessage().getEmbeds().get(0).getFields().get(0).getValue()));
                event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Lobby successfully created", " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue(); // send a message in the channel

            case "discard":
                event.replyEmbeds(CommandEmbedHandler.createCommandEmbed(event, "Lobby creation discarded", " ", ColorHandler.StatusColorEnum.SUCCESS)).setEphemeral(true).queue(); // send a message in the channel
        }
    }
}
