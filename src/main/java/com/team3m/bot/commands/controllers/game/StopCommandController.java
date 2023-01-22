package com.team3m.bot.commands.controllers.game;

import com.team3m.bot.commands.abstracts.Command;
import com.team3m.bot.commands.abstracts.CommandType;
import com.team3m.bot.commands.abstracts.annotations.CommandAnnotation;
import com.team3m.bot.commands.services.game.StopCommandService;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StopCommandController extends Command {
    public StopCommandController() {
        this.name = "stop";
        this.help = "Stops currently active game and resets lobby to default settings";
    }

    @Autowired
    StopCommandService stopCommandService = new StopCommandService();

    @CommandAnnotation(CommandType.SLASH)
    public void startGame(SlashCommandEvent event) {
        stopCommandService.stopGame(event);
    }
}
