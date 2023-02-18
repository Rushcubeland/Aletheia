package fr.rushcubeland.aletheia.utils;

import fr.rushcubeland.aletheia.Aletheia;

public enum JSONKeys {

    CHANNEL_REGLEMENT_ID("channelReglementID", "id"),
    CHANNEL_COMMANDS_ID("channelCommandsID", "id"),
    CHANNEL_JOIN_ID("channelJoinID", "id"),

    SERVER_INFOS("serverInfos", "infos"),

    SERVER_CONNEXION_INFOS("serverConnexionInfos", "infos"),

    MESSAGES_REGLEMENT("messageReglement", "Voici le reglement de notre serveur"),
    TITLE_WELCOME("titleWelcome", "Bienvenue !"),
    MESSAGE_WELCOME("messageWelcome", "Veuillez acceuillir %user% sur le discord ! \nFaites !help pour plus d'informations"),

    RESTART_SCRIPT_PATH("restartScript", "start.sh");

    private final String key;
    private final String default_key;

    JSONKeys(String key, String default_key) {
        this.key = key;
        this.default_key = default_key;
    }

    public String getKey() {
        return key;
    }

    public String getDefault_key() {
        return default_key;
    }

    public String getMessageString(){
        return Aletheia.MESSAGE_RESSOURCES.getString(this.key, this.default_key);
    }

    public String getConfigString(){
        return Aletheia.CONFIGURATION.getString(this.key, this.default_key);
    }
}
