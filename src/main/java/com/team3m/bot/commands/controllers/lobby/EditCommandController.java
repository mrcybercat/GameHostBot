package com.team3m.bot.commands.controllers.lobby;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.abstracts.CommandType;
import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;
import com.team3m.bot.commands.abstracts.annotations.SelectionCommand;
import com.team3m.bot.commands.services.lobby.EditCommandService;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@SelectionCommand
public class EditCommandController extends Command {
    public EditCommandController()
    {
        this.name = "edit";
        this.help = "Allows you to edit game settings";
    }

    @Autowired
    EditCommandService editCommandService = new EditCommandService();

    @CommandAnnotation(CommandType.SLASH)
    public void editLobby(SlashCommandEvent event) {
        editCommandService.editLobby(event);
    }

    @CommandAnnotation(CommandType.SELECTION)
    public void chooseLobbyToEdit(SelectionMenuEvent event){
        editCommandService.chooseLobbyToEdit(event);
    }
}
