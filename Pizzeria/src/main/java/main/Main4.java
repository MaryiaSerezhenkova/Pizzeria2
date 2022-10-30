package main;



import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main4 {

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
             Statement stm = conn.createStatement()){

            int updated = stm.executeUpdate(
                    "INSERT INTO app.menu(\n" +
                            "\tdt_create, dt_update, name, enable)\n" +
                            "\tVALUES (now(), now(), '" + name + "', false);"); //Тут может быть инъекция
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}
