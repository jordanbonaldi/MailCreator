package net.neferett.mailcreator.Commands;

import net.neferett.mailcreator.Annotation.Command;
import net.neferett.mailcreator.Annotation.CommandInterface;
import net.neferett.mailcreator.Mails.MailsManager;

@Command(name = "create", args = 4)
public class CreateMailCommand implements CommandInterface {

    @Override
    public boolean onCommand(MailsManager manager, String... args) {
        String email = args[1];
        String user = args[2];
        String domain = args[3];
        String pass = args[4];

        {
            manager.addMail(email, pass, user, domain);
        }

        System.out.println("Email created !");

        return true;
    }
}
