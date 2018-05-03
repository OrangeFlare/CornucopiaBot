package me.orangeflare.cornucopia;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import me.orangeflare.cornucopia.botModules.*;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class cornucopia {
    private static String BOT_PREFIX = "c!";
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Error with Provided Arguments!");
            System.out.println("  Did you forget to supply the API Token?");
            System.out.println("  Ex: java -jar cornucopia.jar [API Token Here]");
            return;
        }
        IDiscordClient bot = getBuiltDiscordClient(args[0]);
        bot.getDispatcher().registerListener(new cornucopia());
        bot.getDispatcher().registerListener(new coreCommands());
        bot.login();
    }
    private static IDiscordClient getBuiltDiscordClient(String botToken) {
        return new ClientBuilder().withToken(botToken).build();
    }
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
    public static void onMessageReceived(MessageReceivedEvent event) {
        String[] argArray = event.getMessage().getContent().split(" ");
        if (argArray.length == 0) { return; }
        if (!argArray[0].toLowerCase().startsWith(BOT_PREFIX)) { return; }
        String command = argArray[0].substring(2).toLowerCase();
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0);
        switch(command) {
            case "ping":
                coreCommands.pingCommand(event);
                System.out.println("[INFO] 'ping' Command Received!");
                System.out.println("       Command sent by " + getDisplayName(event));
            case "add":
                coreCommands.addCommand(event, argsList);
                System.out.println("[INFO] 'add' Command Received!");
                System.out.println("       Command sent by " + getDisplayName(event));
        }
    }
}
