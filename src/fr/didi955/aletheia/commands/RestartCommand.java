package fr.didi955.aletheia.commands;

import fr.didi955.aletheia.Aletheia;
import fr.didi955.aletheia.utils.Configuration;
import fr.didi955.aletheia.utils.JSONKeys;

import java.io.File;

public class RestartCommand {

    private Aletheia aletheia;

    public RestartCommand(Aletheia aletheia) {
        this.aletheia = aletheia;
    }

    @Command(name="restart", description="Restart le bot", sender=Command.ExecutorType.CONSOLE)
    public void onRestart(){
        String path = JSONKeys.RESTART_SCRIPT_PATH.getConfigString();

        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            Thread shutdownHook = new Thread(() -> {
                try {

                    String os = System.getProperty( "os.name" ).toLowerCase();
                    if ( os.contains( "win")){
                        Runtime.getRuntime().exec( "cmd /c start " + path);
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
            aletheia.setRunning(false);
        }
    }
}
