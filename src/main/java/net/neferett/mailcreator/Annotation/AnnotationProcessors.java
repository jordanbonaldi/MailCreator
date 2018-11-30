package net.neferett.mailcreator.Annotation;

import lombok.Data;
import net.neferett.mailcreator.Console.SimpleCommand;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

@Data
public class AnnotationProcessors {

    private List<Class<? extends CommandInterface>> clazz;

    private List<SimpleCommand> instances;

    public AnnotationProcessors() {
        this.load();
        this.instances = new ArrayList<>();
    }

    private void load() {
        Reflections reflection = new Reflections("net.neferett.mailcreator.Commands");

        this.clazz = new ArrayList<>(reflection.getSubTypesOf(CommandInterface.class));
    }

}
