package me.orangeflare.cornucopia;

import me.orangeflare.cornucopia.botModules.database;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

public class cornucopia {
    public static String BOT_PREFIX = "c!";
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Error with Provided Arguments!");
            System.out.println("  Did you forget to supply the API Token");
            System.out.println("  Ex: java -jar cornucopia.jar [API Token Here]");
            return;
        }
        IDiscordClient bot = getBuiltDiscordClient(args[0]);
        bot.getDispatcher().registerListener(new database());
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
}
