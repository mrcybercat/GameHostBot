package com.teamMMM.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;

@Configuration
public class BotController {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.prefix}")
    private String prefix;

    @Bean
    public JDA load() throws LoginException {
        // todo: everything

        JDA jda = JDABuilder.createDefault(token).build();
        //jda.addEventListener(startEvent);

        return jda;
    }



}



//CommandClient cmdListener = new CommandClientBuilder().setPrefix(prefix).setOwnerId(Long.toString(owner))
//        .addSlashCommands(playCmd, stopCmd, pauseCmd, resumeCmd, skipCmd, nowPlayingCmd, joinCmd).build();
//.setActivity(listening("type /join")).build();
//jda.addEventListener(cmdListener, eventWaiter());
