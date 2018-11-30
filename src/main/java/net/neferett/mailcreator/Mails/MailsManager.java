package net.neferett.mailcreator.Mails;

import lombok.Data;
import lombok.SneakyThrows;
import net.neferett.mailcreator.DataBase.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Data
public class MailsManager {

    private final Manager manager;

    private List<Mail> mails = new ArrayList<>();

    @SneakyThrows
    public void getAllMails() {
        ResultSet set = this.manager.sqlAction("select * from virtual_users").getSet();

        this.mails.clear();

        while (set.next()) {
            this.mails.add(Mail.builder()
                    .id(set.getInt("id"))
                    .domain(set.getInt("domain_id"))
                    .email(set.getString("email"))
                    .user(set.getString("user")).build()
            );
        }
    }

    public void addMail(String email, String pass, String user, String domain) {
        this.manager.sqlUpdateAction(new Manager.Action() {
            @Override
            public String setStatement() {
                return "INSERT INTO virtual_users (password, email, user, domain_id) VALUES (ENCRYPT(?, CONCAT('$6$', SUBSTRING(SHA(RAND()), -16))), ?, ?, ?)";
            }

            @Override
            @SneakyThrows
            public void action(PreparedStatement statement) {
                statement.setString(1, pass);
                statement.setString(2, email);
                statement.setString(3, user);
                statement.setInt(4, Integer.valueOf(domain));
            }
        });
    }
}
