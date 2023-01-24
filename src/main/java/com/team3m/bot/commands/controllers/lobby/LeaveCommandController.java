package com.team3m.bot.commands.controllers.lobby;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.abstracts.CommandType;
import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;
import com.team3m.bot.commands.services.game.StartCommandService;
import com.team3m.bot.commands.services.lobby.LeaveCommandService;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LeaveCommandController extends Command {
    public LeaveCommandController() {
        this.name = "leave";
        this.help = "You will leave the current lobby";
    }

    @Autowired
    LeaveCommandService leaveCommandService = new LeaveCommandService();

    @CommandAnnotation(CommandType.SLASH)
    public void leaveLobby(SlashCommandEvent event) {
        leaveCommandService.leaveLobby(event);
    }

}
