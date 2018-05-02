package me.orangeflare.cornucopia.botModules;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;
import me.orangeflare.cornucopia.cornucopia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class coreCommands {
    public static void sendMessage(IChannel channel, String message) {
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message);
            } catch (DiscordException error){
                System.err.println("[ERROR] Message Failed to send: ");
                error.printStackTrace();
            }
        });
    }
    public static String getDisplayName(MessageReceivedEvent event) { return event.getAuthor().getDisplayName(event.getGuild()) + " (" + event.getAuthor().getName() + ")"; }

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
