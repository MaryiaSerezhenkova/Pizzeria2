package main;



import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main5 {

    public static void main(String[] args) {
        DataSource dataSource = null;
        try {
            dataSource = DataSourceCreator.getInstance();
        } catch (IOException | SQLException | PropertyVetoException e) {
            throw new RuntimeException(e);
        }


        Scanner console = new Scanner(System.in);

        System.out.println("Введите название меню");
        String name = console.nextLine();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stm = conn.prepareStatement("INSERT INTO app.menu(\n" +
                     "\tdt_create, dt_update, name, enable)\n" +
                     "\tVALUES (now(), now(), ?, false);")){

            stm.setString(1, name);

            int updated = stm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}
