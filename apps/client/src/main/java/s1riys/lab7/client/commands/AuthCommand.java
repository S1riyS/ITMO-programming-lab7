package s1riys.lab7.client.commands;

import s1riys.lab7.client.console.IConsole;
import s1riys.lab7.client.network.UDPClient;

public abstract class AuthCommand extends Command {
    protected final UDPClient client;
    public AuthCommand(String name, String description, IConsole console, UDPClient client) {
        super(name, description, console);
        this.client = client;
    }
}
