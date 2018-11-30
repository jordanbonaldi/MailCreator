package net.neferett.mailcreator.Console;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Data
public class Console implements Runnable {

    private final ConsoleHandler handler;

    private BufferedReader reader;

    private void handleCommand(String s) {
        this.handler.newCommand(s);
    }

    @Override
    @SneakyThrows
    public void run() {

        this.reader = new BufferedReader(new InputStreamReader(System.in));

        handler.build().loadCommands();
        handler.loadMailsManager();
        handler.getMailsManager().getAllMails();

        System.out.println("System ready..");

        while (true) {

            Thread.sleep(10L);

            this.handleCommand(this.reader.readLine());

        }

    }
}
