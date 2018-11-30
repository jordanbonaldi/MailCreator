package net.neferett.mailcreator;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import net.neferett.mailcreator.Console.CommandsManager;
import net.neferett.mailcreator.Console.Console;
import net.neferett.mailcreator.Console.ConsoleHandler;
import net.neferett.mailcreator.DataBase.Connector;
import net.neferett.mailcreator.DataBase.Manager;

@Data @Builder
public class Main {

    private ConsoleHandler handler;

    private Runnable console;

    private Connector connector;

    private Manager manager;

    private void launchConsole() {
        new Thread(this.console).start();
    }

    @SneakyThrows
    public static void main(String[] argv) {

        String host = argv[0];
        String user = argv[1];
        String pass = argv[2];
        String db = argv[3];



        Main main = Main.builder()
                .connector(new Connector(host, user, pass, db))
                .build();

        main.setManager(new Manager(main.getConnector()));
        main.setHandler(new ConsoleHandler(main.getManager()));
        main.setConsole(new Console(main.getHandler()));

        main.launchConsole();
    }

}
