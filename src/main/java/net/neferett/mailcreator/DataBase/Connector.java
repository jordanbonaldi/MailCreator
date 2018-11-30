package net.neferett.mailcreator.DataBase;

import lombok.Data;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Data
public class Connector {

    private Connection connection;

    private Statement statement;

    @SneakyThrows
    public Connector(String addr, String user, String password, String db) {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://" + addr + "/" + db, user, password);
        this.statement = this.connection.createStatement();
    }

}
