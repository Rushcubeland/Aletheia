package fr.didi955.aletheia.commands;

public enum CommandsUnit {

    HELP("help", "Liste toutes les commandes", Command.ExecutorType.USER, HelpCommand.class),
    STOP("stop", "Stopper le bot", Command.ExecutorType.CONSOLE, StopCommand.class),
    RESTART("restart", "Restart le bot", Command.ExecutorType.CONSOLE, RestartCommand.class),
    INFO("info", "En savoir plus sur Rushcubland", Command.ExecutorType.USER, InfoCommand.class);

    private final String name;
    private final String description;
    private final Command.ExecutorType executorType;
    private final Class clazz;

    CommandsUnit(String name, String description, Command.ExecutorType executorType, Class clazz) {
        this.name = name;
        this.description = description;
        this.executorType = executorType;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Command.ExecutorType getExecutorType() {
        return executorType;
    }

    public Class getClazz() {
        return clazz;
    }
}
