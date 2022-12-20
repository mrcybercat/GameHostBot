package com.teamMMM.bot;

import com.teamMMM.events.StartEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
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
        System.out.println("Do thing please");
        //JDA jda = JDABuilder.createDefault("MTA1MTE2NTMwMTc3MDgxMzU1MA.GHeuq-.uAUMxgMQ_9X1c1giAjKaQj6KM-3ui-ddD4E-ko")
                //.enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
        //        .build();
        JDA jda = JDABuilder.createDefault("MTA1MTE2NTMwMTc3MDgxMzU1MA.GHeuq-.uAUMxgMQ_9X1c1giAjKaQj6KM-3ui-ddD4E-ko").build();
        jda.addEventListener(new StartEvent());

        return jda;
    }



}



//CommandClient cmdListener = new CommandClientBuilder().setPrefix(prefix).setOwnerId(Long.toString(owner))
//        .addSlashCommands(playCmd, stopCmd, pauseCmd, resumeCmd, skipCmd, nowPlayingCmd, joinCmd).build();
//.setActivity(listening("type /join")).build();
//jda.addEventListener(cmdListener, eventWaiter());
