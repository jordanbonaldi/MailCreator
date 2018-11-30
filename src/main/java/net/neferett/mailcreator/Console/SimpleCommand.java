package net.neferett.mailcreator.Console;

import lombok.Data;
import net.neferett.mailcreator.Annotation.CommandInterface;
import net.neferett.mailcreator.Mails.MailsManager;

@Data
public class SimpleCommand {

    private final CommandInterface instance;

    private final String name;

    private final int args;

    private boolean success;

    public boolean executeCommand(MailsManager manager, String line) {

        String[] args = line.split(" ");

        if (!args[0].equalsIgnoreCase("") && args.length != this.args) {
            System.out.println("Wrong number of arguments!");
            return false;
        }

        this.success = this.instance.onCommand(manager, args);

        return this.success;
    }

}
