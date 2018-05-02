package me.orangeflare.cornucopia;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import me.orangeflare.cornucopia.botModules.*;

public class cornucopia {
    public static String BOT_PREFIX = "c!";
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Error with Provided Arguments!");
            System.out.println("  Did you forget to supply the API Token?");
            System.out.println("  Ex: java -jar cornucopia.jar [API Token Here]");
            return;
        }
        IDiscordClient bot = getBuiltDiscordClient(args[0]);
        bot.getDispatcher().registerListener(new coreCommands());
        bot.login();
    }
    private static IDiscordClient getBuiltDiscordClient(String botToken) {
        return new ClientBuilder().withToken(botToken).build();
    }
}
