package com.example.stonecollection;
import com.example.stonecollection.models.StoneCollection;
import com.example.stonecollection.models.Stone;
import com.example.stonecollection.models.Color;
import com.example.stonecollection.models.Rarity;
import com.example.stonecollection.models.Precious;
import com.example.stonecollection.models.SemiPrecious;
import com.example.stonecollection.models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class DB {
    private final Connection connection;
    private final String url = "jdbc:mysql://localhost/stones";
    private final String username = "root";
    private final String password = "nastia!2006";

    public static StoneCollection collection ;

    public static Stone stone;
    DB() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);

    }

    public void addCollection(StoneCollection collection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT stonecollection(name) VALUES (\'%s\')", collection.getName()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editCollection(StoneCollection collection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE stonecollection SET name = \'%s\' where id=%d", collection.getName(),collection.getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCollection(StoneCollection collection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM stonecollection WHERE Id = " + collection.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public ArrayList<StoneCollection> getCollections() {
        ArrayList<StoneCollection> collections = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM stonecollection");
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                collections.add(new StoneCollection(id, name,getStones(id)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return collections;

    }

    public int getIdColor(Color color) {
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(String.format("SELECT id FROM color WHERE name =\'%s\' ", color.name()));
            resultSet.next();
            int id = resultSet.getInt(1);
            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public int getIdRarity(Rarity rarity) {
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(String.format("SELECT id FROM rarity WHERE type =\'%s\' ", rarity.name()));
            resultSet.next();
            int id = resultSet.getInt(1);
            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }


//    дописати селект щоб виводився колір і тип рідкості замість їхніх ід, такий самий селект написати
//    для напівдорогоцінних каменів і в інтелідж ідеї дістати дані і запхати в еррей ліст(в тебе там буде два while)


    public ArrayList<Stone> getStones(int id) {
        ArrayList<Stone> stones = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("\n" +
                    "SELECT stone.id,stone.name, color.name,stone.price,stone.weight,quality ,stone.stonecollection_id FROM stones.semiprecious inner join stone on stone_id=stone.id inner join color on color_id=color.id where stonecollection_id=" + id);
            while (resultSet.next()) {

                int id_s = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Color color = Color.valueOf(resultSet.getString(3));
                double price = resultSet.getDouble(4);
                int weight = resultSet.getInt(5);
                int quality = resultSet.getInt(6);
                int idc = resultSet.getInt(7);
                stones.add(new SemiPrecious(id_s, quality, name, price, color, weight, idc));
            }


            resultSet = statement.executeQuery("\n" +
                    "SELECT stone.id,stone.name, color.name,stone.price,stone.weight,stone.stonecollection_id,rarity.type\n" +
                    "FROM stones.precious inner join stone on stone_id=stone.id\n" +
                    "inner join color on color_id=color.id\n" +
                    "inner join rarity on rarity_id=rarity.id\n" +
                    "where stonecollection_id=" + id);
            while (resultSet.next()) {

                int id_s = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Color color = Color.valueOf(resultSet.getString(3));
                double price = resultSet.getDouble(4);
                int weight = resultSet.getInt(5);
                Rarity rarity = Rarity.valueOf(resultSet.getString(7));
                int idc = resultSet.getInt(6);
                stones.add(new Precious(id_s, name, price, color, weight, rarity, idc));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return stones;

    }

    //    додати метод щоб додавати дорогоцінний камінь в бд
    public void addPrecious(Precious precious) {

        try {
            Statement statement = connection.createStatement();
            String q="INSERT INTO `stones`.`stone` (`name`, `price`, `weight`, `color_id`, `stonecollection_id`) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement st1=connection.prepareStatement(q);
            st1.setString(1,precious.getName());
            st1.setDouble(2,precious.getPrice());
            st1.setInt(3,precious.getWeight());
            st1.setInt(4,getIdColor(precious.getColor()));
            st1.setInt(5,precious.getStonecollection_id());
            st1.executeUpdate();

            String query="SELECT max(stone.id) FROM stone";
            Statement st=connection.createStatement();
            ResultSet resultSet=st.executeQuery(query);
            resultSet.next();
            int stoneid=resultSet.getInt(1);

            if(stoneid!=-1)
                statement.executeUpdate(String.format("INSERT INTO `stones`.`precious` (`rarity_id`, `stone_id`) VALUES ('%d', '%d');", getIdRarity(precious.getRarity()), stoneid ));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


//    додати метод щоб додавати напівдорогоцінний камінь в бд

    public void addSemiPrecious(SemiPrecious semiprecious) {
        try {
            Statement statement = connection.createStatement();
            String q="INSERT INTO `stones`.`stone` (`name`, `price`, `weight`, `color_id`, `stonecollection_id`) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement st1=connection.prepareStatement(q);
            st1.setString(1,semiprecious.getName());
            st1.setDouble(2,semiprecious.getPrice());
            st1.setInt(3,semiprecious.getWeight());
            st1.setInt(4,getIdColor(semiprecious.getColor()));
            st1.setInt(5,semiprecious.getStonecollection_id());
            st1.executeUpdate();

            String query="SELECT max(stone.id) FROM stone";
            Statement st=connection.createStatement();
            ResultSet resultSet=st.executeQuery(query);
            resultSet.next();
            int stoneid=resultSet.getInt(1);

            if(stoneid!=-1)
                statement.executeUpdate(String.format("INSERT INTO `stones`.`semiprecious` (`quality`, `stone_id`) VALUES ('%d', '%d');", semiprecious.getQuality(), stoneid ));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    додати метод щоб редагувати дорогоцінний камінь в бд

    public void editPrecious(Precious precious) {

        try {
            PreparedStatement st=connection.prepareStatement(" UPDATE `stones`.`stone` SET `name` = ?, `price` = ?, `weight` = ?, `color_id` = ? WHERE (`id` = ?);");
            st.setString(1,precious.getName());
            st.setDouble(2,precious.getPrice());
            st.setInt(3,precious.getWeight());
            st.setInt(4,getIdColor(precious.getColor()));
            st.setInt(5,precious.getId());
            st.executeUpdate();

            PreparedStatement st1=connection.prepareStatement("UPDATE `stones`.`precious` SET `rarity_id` = ? WHERE (`stone_id` = ?);");
            st1.setInt(1,getIdRarity(precious.getRarity()));
            st1.setInt(2,precious.getId());
            st1.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


//    додати метод щоб редагувати напівдорогоцінний камінь в бд

    public void editSemiPrecious(SemiPrecious semiPrecious) {

        try {
            PreparedStatement st=connection.prepareStatement(" UPDATE `stones`.`stone` SET `name` = ?, `price` = ?, `weight` = ?, `color_id` = ? WHERE (`id` = ?);");
            st.setString(1,semiPrecious.getName());
            st.setDouble(2,semiPrecious.getPrice());
            st.setInt(3,semiPrecious.getWeight());
            st.setInt(4,getIdColor(semiPrecious.getColor()));
            st.setInt(5,semiPrecious.getId());
            st.executeUpdate();

            PreparedStatement st1=connection.prepareStatement("UPDATE `stones`.`semiprecious` SET `quality` = ? WHERE (`stone_id` = ?);");
            st1.setInt(1,semiPrecious.getQuality());
            st1.setInt(2,semiPrecious.getId());
            st1.executeUpdate();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


//    додати метод щоб видаляти дорогоцінний камінь з бд

    public void deleteStone(Stone stone) {
//        DELETE FROM `stones`.`stone` WHERE (`id` = '12');
//        if(stone instanceof Precious){
        try {

            PreparedStatement statement1=connection.prepareStatement("DELETE FROM `stones`.`stone` WHERE (`id` = ?)");
            System.out.println(stone.getId());
            statement1.setInt(1,stone.getId());
            statement1.executeUpdate();
//            int rows2 = statement.executeUpdate("DELETE FROM `stones`.`stone` WHERE `id` = " + stone.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        } }
//        else{
//            try {
//                Statement statement = connection.createStatement();
//             //   int rows = statement.executeUpdate("DELETE FROM `stones`.`semiprecious` WHERE `stone_id` = " + stone.getId());
//                int rows2 = statement.executeUpdate("DELETE FROM `stones`.`stone` WHERE `id` = " + stone.getId());
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
        System.out.println(stone.getId());
    }

public String getPasswordByUsername(String username) {

    try {

        PreparedStatement query = connection.prepareStatement("SELECT password FROM user where username=?");
        query.setString(1, username);
        ResultSet resultSet = query.executeQuery();
        resultSet.next();
        String password = resultSet.getString(1);
        return password;
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

return null;
}

public void addUser(User user){
    String passwordToHash = user.getPassword();
    String generatedPassword = null;

    try
    {
        // Create MessageDigest instance for MD5
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Add password bytes to digest
        md.update(passwordToHash.getBytes());

        // Get the hash's bytes
        byte[] bytes = md.digest();

        // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        // Get complete hashed password in hex format
        generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
        try{

            Statement statement=connection.createStatement();
            String q="INSERT INTO `stones`.`user` (`username`, `password`) VALUES (?, ?)";
            PreparedStatement st=connection.prepareStatement(q);
            st.setString(1,user.getUsername());
            st.setString(2,generatedPassword);
            st.executeUpdate();
        }

        catch(Exception e){
            System.out.println(e.getMessage());
        }


}




}
