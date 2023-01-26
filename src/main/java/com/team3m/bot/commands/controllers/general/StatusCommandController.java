package com.team3m.bot.commands.controllers.general;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.abstracts.CommandType;
import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;
import com.team3m.bot.commands.services.general.AboutCommandService;
import com.team3m.bot.commands.services.general.StatusCommandService;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StatusCommandController extends Command {
    public StatusCommandController() {
        this.name = "status";
        this.help = "Gives some debug info";
    }

    @Autowired
    StatusCommandService statusCommandService = new StatusCommandService();

    @CommandAnnotation(CommandType.SLASH)
    public void status(SlashCommandEvent event) {
        statusCommandService.status(event);
    }
}
