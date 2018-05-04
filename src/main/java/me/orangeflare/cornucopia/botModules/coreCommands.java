package me.orangeflare.cornucopia.botModules;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import me.orangeflare.cornucopia.cornucopia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.orangeflare.cornucopia.cornucopia.getDisplayName;
import static me.orangeflare.cornucopia.cornucopia.sendMessage;

public class coreCommands {
    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] argArray = event.getMessage().getContent().split(" ");
        if (argArray.length == 0) { return; }
        if (!argArray[0].toLowerCase().startsWith(cornucopia.BOT_PREFIX)) { return; }
        String command = argArray[0].substring(2).toLowerCase();
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0);
        switch(command) {
            case "ping":
                pingCommand(event);
                System.out.println("[INFO] 'ping' Command Received!");
                System.out.println("       Command sent by " + getDisplayName(event));
        }
    }
    private void pingCommand(MessageReceivedEvent event){
        sendMessage(event.getChannel(), "Pong!");
    }
}
