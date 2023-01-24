package com.team3m.bot.commands.controllers.lobby;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.abstracts.CommandType;
import com.team3m.bot.commands.abstracts.annotations.ButtonCommand;
import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;
import com.team3m.bot.commands.abstracts.annotations.SelectionCommand;
import com.team3m.bot.commands.services.lobby.JoinCommandService;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ButtonCommand
@SelectionCommand
public class JoinCommandController extends Command {
    public JoinCommandController() {
        this.name = "join";
        this.help = "This command gives you a list of all lobbies in guild";
    }

    @Autowired
    JoinCommandService joinCommandService = new JoinCommandService();

    @CommandAnnotation(CommandType.SLASH)
    public void joinLobby(SlashCommandEvent event) {
        joinCommandService.joinLobby(event);
    }

    @CommandAnnotation(CommandType.BUTTON)
    public void rejoinLobby(ButtonClickEvent event) {
        joinCommandService.rejoinLobby(event);
    }

    @CommandAnnotation(CommandType.SELECTION)
    public void chooseLobbyToJoin(SelectionMenuEvent event){
        joinCommandService.chooseLobbyToJoin(event);
    }
}
