package com.team3m.bot.commands.controllers.game;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;
import com.team3m.bot.commands.abstracts.CommandType;
import com.team3m.bot.commands.services.game.StartCommandService;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StartCommandController extends Command {
    public StartCommandController() {
        this.name = "start";
        this.help = "Start the game if given the setup is complete";
    }

    @Autowired
    StartCommandService startCommandService = new StartCommandService();

    @CommandAnnotation(CommandType.SLASH)
    public void startGame(SlashCommandEvent event) {
        startCommandService.startGame(event);
    }
}
