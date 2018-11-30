package net.neferett.mailcreator.Console;

import lombok.Data;
import net.neferett.mailcreator.Annotation.AnnotationProcessors;
import net.neferett.mailcreator.DataBase.Manager;
import net.neferett.mailcreator.Mails.MailsManager;

@Data
public class ConsoleHandler {

    private final Manager dataBaseManager;

    private AnnotationProcessors processors;

    private CommandsManager manager;

    private MailsManager mailsManager;

    public void loadMailsManager() {
        this.mailsManager = new MailsManager(this.dataBaseManager);
    }

    public ConsoleHandler build() {
        this.processors = new AnnotationProcessors();
        this.manager = new CommandsManager(this.processors.getClazz());

        return this;
    }

    public ConsoleHandler loadCommands(){
        this.manager.createInstances();

        return this;
    }

    public void newCommand(String string){
        SimpleCommand command = this.manager.getCommand(string);

        if (command == null) {
            System.out.println("Unknown command " + string);
            return;
        }

        string = string.substring(command.getName().length());

        command.executeCommand(this.mailsManager, string);
    }



}
