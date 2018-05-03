package me.orangeflare.cornucopia;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
//import me.orangeflare.cornucopia.botModules.*;
import me.orangeflare.cornucopia.googleFirebase.*;
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
    public static void main(String[] args) throws java.io.IOException {
        if (args.length != 2) {
            System.err.println("Error with Provided Arguments!");
            System.out.println("  Did you forget to supply the API Token/Firebase Project ID?");
            System.out.println("  Ex: java -jar cornucopia.jar [API Token Here] [Firebase Project ID]");
            return;
        }
        firebase.init(args[1]);
        IDiscordClient bot = getBuiltDiscordClient(args[0]);
        bot.getDispatcher().registerListener(new cornucopia());
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
                System.err.println("        " + error.getErrorMessage());
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
                pingCommand(event);
                System.out.println("[INFO] 'ping' Command Received!");
                System.out.println("       Command sent by " + getDisplayName(event));
        }
    }
    public static void pingCommand(MessageReceivedEvent event){ sendMessage(event.getChannel(), "Pong!"); }
}
