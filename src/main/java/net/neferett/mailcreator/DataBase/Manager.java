package net.neferett.mailcreator.DataBase;

import lombok.Data;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Data
public class Manager {

    private final Connector connector;

    private ResultSet set;

    public interface Action {
        String setStatement();
        void action(PreparedStatement statement);
    }

    @SneakyThrows
    public Manager sqlAction(String action) {
        this.set = this.connector.getStatement().executeQuery(action);

        return this;
    }

    @SneakyThrows
    public Manager sqlUpdateAction(Action action) {
        PreparedStatement statement = this.connector.getConnection().prepareStatement(action.setStatement());

        action.action(statement);

        statement.executeUpdate();
        statement.close();

        return this;
    }
}
