package net.neferett.mailcreator.Console;

import lombok.Data;
import lombok.SneakyThrows;
import net.neferett.mailcreator.Annotation.Command;
import net.neferett.mailcreator.Annotation.CommandInterface;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommandsManager {

    private final List<Class<? extends CommandInterface>> clazz;

    private List<SimpleCommand> commands = new ArrayList<>();

    @SneakyThrows
    private CommandInterface createObject(Class clazz) {
        Constructor constructor = clazz.getConstructors()[0];

        return (CommandInterface) constructor.newInstance();
    }

    public void createInstances() {
        this.clazz.forEach(l -> {
            Command command = l.getAnnotation(Command.class);
            if (command.args() >= 0) {

                System.out.println("Loading command " + command.name());

                CommandInterface instance = this.createObject(l);

                this.commands.add(new SimpleCommand(instance, command.name(), command.args()));
            }
        });
    }

    public SimpleCommand getCommand(String string) {
        String name = string.split(" ")[0];

        return this.commands.stream().filter(e -> e.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
