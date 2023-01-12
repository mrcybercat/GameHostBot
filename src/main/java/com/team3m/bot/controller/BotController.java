package com.team3m.bot.controller;

import com.team3m.bot.commands.EditGameSettingsCmd;
import com.team3m.bot.commands.JoinGameCmd;
import com.team3m.bot.commands.StartGameCmd;
import com.team3m.bot.events.StartEvent;
import com.team3m.bot.events.StatusEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class BotController {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.prefix}")
    private String prefix;


    @Bean
    public JDA load() throws LoginException {
        // todo: everything

        JDA jda = JDABuilder
                .createDefault(token)
                .enableIntents(GatewayIntent.DIRECT_MESSAGES)
                .enableIntents(GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new StartEvent())
                .build();

        jda.addEventListener(new StatusEvent());

        jda.addEventListener(new StartGameCmd(), new JoinGameCmd(), new EditGameSettingsCmd());
        return jda;
    }
}

//CommandClientBuilder client = new CommandClientBuilder();
//
//
// client.setPrefix("!");
/*
        client.addCommands(
                // command to show information about the bot
                // new AboutCommand(Color.BLUE, "an example bot",
                //        new String[]{"Cool commands","Nice examples","Lots of fun!"},
                //        new Permission[]{Permission.ADMINISTRATOR}),

                // command to show a random cat
                new StartGameCmd(),

                // command to make a random choice
                new JoinGameCmd(),

                // command to say hello
                new EditGameSettingsCmd()

                // command to check bot latency
                //new PingCommand(),

                // command to shut off the bot
                //new ShutdownCommand()
                );

 */
//CommandClient cmdListener = new CommandClientBuilder().setPrefix(prefix).setOwnerId(Long.toString(owner))
//        .addSlashCommands(playCmd, stopCmd, pauseCmd, resumeCmd, skipCmd, nowPlayingCmd, joinCmd).build();
//.setActivity(listening("type /join")).build();
//jda.addEventListener(cmdListener, eventWaiter());
//JDA jda = JDABuilder.createDefault("MTA1MTE2NTMwMTc3MDgxMzU1MA.GHeuq-.uAUMxgMQ_9X1c1giAjKaQj6KM-3ui-ddD4E-ko")
//.enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
//        .build();