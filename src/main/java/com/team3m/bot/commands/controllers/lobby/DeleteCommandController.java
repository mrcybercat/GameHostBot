package com.team3m.bot.commands.controllers.lobby;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.abstracts.CommandType;
import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;
import com.team3m.bot.commands.services.lobby.DeleteCommandService;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DeleteCommandController extends Command {
    public DeleteCommandController() {
        this.name = "delete";
        this.help = "Deletes the lobby you owns";
    }

    @Autowired
    DeleteCommandService deleteCommandService = new DeleteCommandService();

    @CommandAnnotation(CommandType.SLASH)
    public void deleteLobby(SlashCommandEvent event) {
        deleteCommandService.deleteLobby(event);
    }

}
