package com.team3m.bot.commands.controllers.lobby;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.abstracts.CommandType;
import com.team3m.bot.commands.abstracts.annotations.ButtonCommand;
import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;
import com.team3m.bot.commands.services.lobby.CreateCommandService;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ButtonCommand
public class CreateCommandController extends Command {
    public CreateCommandController() {
        this.name = "create";
        this.help = "Creates a lobby for you and your friends";

        this.optionsData.add(
                new OptionData(OptionType.STRING, "name", "Input lobby name of your choosing", true)
        );
    }

    @Autowired
    CreateCommandService createCommandService = new CreateCommandService();

    @CommandAnnotation(CommandType.SLASH)
    public void createLobby(SlashCommandEvent event) {
        createCommandService.createLobby(event);
    }

    @CommandAnnotation(CommandType.BUTTON)
    public void confirmCreation(ButtonClickEvent event) {
        createCommandService.confirmCreation(event);
    }
}
