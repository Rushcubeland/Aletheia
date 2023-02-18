package fr.rushcubeland.aletheia;

import fr.rushcubeland.aletheia.commands.CommandManager;
import fr.rushcubeland.aletheia.jda.JDAManager;
import fr.rushcubeland.aletheia.listeners.*;
import fr.rushcubeland.aletheia.utils.Configuration;
import fr.rushcubeland.aletheia.utils.JSONKeys;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Aletheia implements Runnable {

    public static final Configuration CONFIGURATION;
    public static final Configuration MESSAGE_RESSOURCES;
    private boolean running;
    private final Scanner scanner = new Scanner(System.in);

    public Aletheia() {
        if(MESSAGE_RESSOURCES == null){
            System.out.println("Le fichier de configuration des messages n'a pas été chargé !");
            return;
        }
        if(CONFIGURATION == null){
            System.out.println("Le fichier de configuration n'a pas été chargé !");
            return;
        }

        if(JDAManager.getShardManager() == null){
            System.out.println("Le bot n'a pas pu se connecter !");
            CONFIGURATION.save();
            MESSAGE_RESSOURCES.save();
            return;
        }

        registerListeners();

        JDAManager.getShardManager().setStatus(OnlineStatus.ONLINE);
        JDAManager.getShardManager().setActivity(Activity.playing("Rushcubeland - En développement - 0/450"));

    }

    static {
        Configuration configuration = null;
        Configuration configuration_msg = null;

        try {
            configuration = new Configuration("config.json");
        }
        catch (IOException e){
            e.printStackTrace();
        }

        CONFIGURATION = configuration;

        try {
            configuration_msg = new Configuration("messages.json");
        }
        catch (IOException e){
            e.printStackTrace();
        }

        MESSAGE_RESSOURCES = configuration_msg;
    }

    public static void main(String... args){

        Aletheia aletheia = new Aletheia();
        new Thread(aletheia, "aletheia").start();

    }

    @Override
    public void run() {
        running = true;


        while (running){
            if(scanner.hasNextLine()){
                checkConsoleCommands(scanner.nextLine());
            }
        }
        scanner.close();
        JDAManager.getShardManager().setStatus(OnlineStatus.OFFLINE);
        JDAManager.getShardManager().setActivity(null);
        JDAManager.getShardManager().shutdown();
        System.out.println("Bot stopped");
        System.exit(0);
    }

    private void registerListeners(){
        JDAManager.getShardManager().addEventListener(new CommandManager());
        JDAManager.getShardManager().addEventListener(new MessageListener());
        JDAManager.getShardManager().addEventListener(new JoinEvent());
        JDAManager.getShardManager().addEventListener(new QuitEvent());
        JDAManager.getShardManager().addEventListener(new LoadedEvent());
        JDAManager.getShardManager().addEventListener(new AddReactionEvent());
        JDAManager.getShardManager().addEventListener(new RemoveReactionEvent());
    }

    private void checkConsoleCommands(String input){
        if(input.equals("stop")){
            setRunning(false);
        }
        else if(input.equals("restart")){
            restart();
        }

    }

    public void restart(){
        String path = JSONKeys.RESTART_SCRIPT_PATH.getConfigString();

        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            Thread shutdownHook = new Thread(() -> {
                try {

                    String os = System.getProperty( "os.name" ).toLowerCase();
                    if ( os.contains( "win")){
                        Runtime.getRuntime().exec( new String[]{"cmd /c start " + path});
                    } else {
                        Runtime.getRuntime().exec( new String[]
                                {
                                        "bash", f.getPath()
                                });
                    }
                } catch ( Exception e){
                    e.printStackTrace();
                }
            });
            shutdownHook.setDaemon(true);
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            setRunning(false);
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
