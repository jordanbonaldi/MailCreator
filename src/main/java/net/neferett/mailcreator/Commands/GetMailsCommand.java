package net.neferett.mailcreator.Commands;

import net.neferett.mailcreator.Annotation.Command;
import net.neferett.mailcreator.Annotation.CommandInterface;
import net.neferett.mailcreator.Mails.MailsManager;

@Command(name = "getMails", args = 0)
public class GetMailsCommand implements CommandInterface {

    @Override
    public boolean onCommand(MailsManager manager, String... args) {

        manager.getAllMails();

        manager.getMails().forEach(e ->
            System.out.println("ID : " + e.getId() + "  -  Domain : " + e.getDomain() + "  -  Email : " + e.getEmail() + "  -  User : " + e.getUser())
        );

        return true;
    }
}
