package net.neferett.mailcreator.Annotation;

import net.neferett.mailcreator.Mails.MailsManager;

public interface CommandInterface {

    boolean onCommand(MailsManager manager, String ... args);

}
