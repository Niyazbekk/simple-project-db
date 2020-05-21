import java.sql.*;
import java.util.Scanner;


public class Main {

    public Main() throws SQLException {



    }




    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        int a = 0;
        try {

            // Load the Oracle JDBC driver

            String driverName = "oracle.jdbc.driver.OracleDriver";

            Class.forName(driverName);

            // Create a connection to the database

            String serverName = "localhost";

            String serverPort = "1521";

            String sid = "XE";

            String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;

            String username = "sys as sysdba";

            String password = "1708";

            connection = DriverManager.getConnection(url, username, password);
            DatabaseMetaData dbmd = connection.getMetaData();
            System.out.println("Successfully Connected to the database!");
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverVersion());
            System.out.println("Database Product Name: " + dbmd.getDatabaseProductName());
            System.out.println("Database Product Version: " + dbmd.getDatabaseProductVersion());

            Statement statement = null;

            try {
                statement = connection.createStatement(
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE

                );



                System.out.println("Enter the name: ");
                Scanner sc = new Scanner(System.in);
                String user = sc.next();
                System.out.println("Enter the password: ");
                Scanner Ps = new Scanner(System.in);
                int pass = Ps.nextInt();

                if(user.equals("admin") && pass == 123){
                    admins();
                }

                if(user.equals("user") && pass == 123){
                    Users();
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }




    }

    public static void admins() {

        Connection connection = null;
        int a = 0;

        try {
            // Load the Oracle JDBC driver

            String driverName = "oracle.jdbc.driver.OracleDriver";

            Class.forName(driverName);

            // Create a connection to the database

            String serverName = "localhost";

            String serverPort = "1521";

            String sid = "XE";

            String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;

            String username = "sys as sysdba";

            String password = "1708";

            connection = DriverManager.getConnection(url, username, password);
            DatabaseMetaData dbmd = connection.getMetaData();

            Statement statement = null;

            try {
                statement = connection.createStatement(
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE

                );


                Scanner sc = new Scanner(System.in);

                System.out.println("Choose an action ");

                System.out.println("1) Show all records (users)  ");

                System.out.println("2) Show all records (corp)  ");

                System.out.println("3) Add new Record  ");

                System.out.println("4) Remove a record  ");

                System.out.println("5)Search a record  ");

                System.out.println("6)exit ");



                int CH = sc.nextInt();

                if (CH == 1) {                             //ALL FROM USErs

                    String yourQry1 = "select * from usersrecords";

                    ResultSet resultSet = statement.executeQuery(yourQry1);


                    while(resultSet.next()) {
                        String FN = resultSet.getString("First_name");
                        String A = resultSet.getString("PersonId");
                        String B = resultSet.getString("City");
                        String C = resultSet.getString("Language");


                        System.out.println("PersonId :" + A);
                        System.out.println("Name :" + FN);
                        System.out.println("City :" + B);
                        System.out.println("Language :" + C);
                    }


                    System.out.println("Back?");
                    Scanner Qs = new Scanner(System.in);
                    String Back = Qs.next();
                    if(Back.equals("yes") || Back.equals("y"))
                    {
                     admins();
                    }

                }
                if (CH == 2) {                            //ALL FROM CORP

                    String yourQry1 = "select * from adminsrecords";

                    ResultSet resultSet = statement.executeQuery(yourQry1);


                    while(resultSet.next()) {
                        String FN = resultSet.getString("CORPNAME");
                        String A = resultSet.getString("CORPID");
                        String B = resultSet.getString("City");
                        String C = resultSet.getString("Language");


                        System.out.println("CorpId :" + A);
                        System.out.println("CorpName :" + FN);
                        System.out.println("City :" + B);
                        System.out.println("Language :" + C);
                    }


                    System.out.println("Back?");
                    Scanner Qs = new Scanner(System.in);
                    String Back = Qs.next();
                    if(Back.equals("yes") || Back.equals("y"))
                    {
                        admins();
                    }

                }
                if (CH == 3) {                            // ADD

                    Scanner s = new Scanner(System.in);


                    System.out.println("PersonId :");
                    int id = s.nextInt();
                    System.out.println("First name :");
                    String F_name = s.next();
                    System.out.println("Last name :");
                    String L_name = s.next();
                    System.out.println("City :");
                    String City = s.next();
                    System.out.println("Language :");
                    String Language = s.next();



                    String yourQry1 = String.format("INSERT Into Usersrecords (PersonId , first_name , Last_name , City , Language) VALUES ('%s' , '%s' , '%s' , '%s' , '%s')" , id , F_name ,L_name , City , Language);


                    ResultSet resultSet = statement.executeQuery(yourQry1);

                    resultSet.next();

                    connection.commit();


                    System.out.println("Back?");

                    Scanner Qs = new Scanner(System.in);

                    String Back = Qs.next();

                    if(Back.equals("yes") || Back.equals("y"))
                    {
                        admins();
                    }
                }

                if (CH == 4) {                    //REMOVE

                    Scanner s = new Scanner(System.in);
                    System.out.println("First name :");
                    String F_name = s.next();

                    String yourQry1 = String.format("DELETE from usersrecords where first_name = '%s'" , F_name);

                    ResultSet resultSet = statement.executeQuery(yourQry1);

                    resultSet.next();

                    connection.commit();

                    System.out.println("Back?");

                    Scanner Qs = new Scanner(System.in);

                    String Back = Qs.next();

                    if(Back.equals("yes") || Back.equals("y"))
                    {
                        admins();
                    }
                }
                if (CH == 5) {                   //SEARHING
                    Scanner s = new Scanner(System.in);
                    System.out.println("First name :");
                    String F_name = s.next();

                    String yourQry1 = String.format("Select * from usersrecords where first_name = '%s'" , F_name);

                    //String yourQry1 = String.format("select *  from  usersrecords u join adminsrecords a on u.city =  a.city where  u.Language = '%s' and a.Language = '%s';" , F_name , F_name);
                    // NOT WORKING AND I DONT KNOW WHY

                    ResultSet resultSet = statement.executeQuery(yourQry1);

                    while(resultSet.next()) {
                        String FN = resultSet.getString("First_name");
                        String A = resultSet.getString("PersonId");
                        String B = resultSet.getString("City");
                        String C = resultSet.getString("Language");


                        System.out.println("PersonId :" + A);
                        System.out.println("Name :" + FN);
                        System.out.println("City :" + B);
                        System.out.println("Language :" + C);
                    }

                    System.out.println("Back?");

                    Scanner Qs = new Scanner(System.in);

                    String Back = Qs.next();

                    if(Back.equals("yes") || Back.equals("y"))
                    {
                        admins();
                    }

                }


                if (CH == 6) {
                    String[] d = new String[1];
                    main(d);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Users(){

        Connection connection = null;
        int a = 0;

        try {
            // Load the Oracle JDBC driver

            String driverName = "oracle.jdbc.driver.OracleDriver";

            Class.forName(driverName);

            // Create a connection to the database

            String serverName = "localhost";

            String serverPort = "1521";

            String sid = "XE";

            String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;

            String username = "sys as sysdba";

            String password = "1708";

            connection = DriverManager.getConnection(url, username, password);
            DatabaseMetaData dbmd = connection.getMetaData();

            Statement statement = null;

            try {
                statement = connection.createStatement(
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE

                );


                Scanner sc = new Scanner(System.in);

                System.out.println("Choose an action ");

                System.out.println("1) Show all records ");

                System.out.println("2) Add new Record  ");

                System.out.println("3) Search a record  ");

                System.out.println("4)exit ");



                int CH = sc.nextInt();


                if (CH == 1) {
                    String yourQry1 = "select * from usersrecords";

                    ResultSet resultSet = statement.executeQuery(yourQry1);


                    while(resultSet.next()) {
                        String FN = resultSet.getString("First_name");
                        String A = resultSet.getString("PersonId");
                        String B = resultSet.getString("City");
                        String C = resultSet.getString("Language");


                        System.out.println("PersonId :" + A);
                        System.out.println("Name :" + FN);
                        System.out.println("City :" + B);
                        System.out.println("Language :" + C);
                    }


                    System.out.println("Back?");
                    Scanner Qs = new Scanner(System.in);
                    String Back = Qs.next();
                    if(Back.equals("yes") || Back.equals("y"))
                    {
                        Users();
                    }
                }


                if (CH == 2) {
                    Scanner s = new Scanner(System.in);


                    System.out.println("PersonId :");
                    int id = s.nextInt();
                    System.out.println("First name :");
                    String F_name = s.next();
                    System.out.println("Last name :");
                    String L_name = s.next();
                    System.out.println("City :");
                    String City = s.next();
                    System.out.println("Language :");
                    String Language = s.next();



                    String yourQry1 = String.format("INSERT Into Usersrecords (PersonId , first_name , Last_name , City , Language) VALUES ('%s' , '%s' , '%s' , '%s' , '%s')" , id , F_name ,L_name , City , Language);


                    ResultSet resultSet = statement.executeQuery(yourQry1);

                    resultSet.next();

                    connection.commit();


                    System.out.println("Back?");

                    Scanner Qs = new Scanner(System.in);

                    String Back = Qs.next();

                    if(Back.equals("yes") || Back.equals("y"))
                    {
                        Users();
                    }
                }


                if (CH == 3) {
                    Scanner s = new Scanner(System.in);
                    System.out.println("First name :");
                    String F_name = s.next();

                    String yourQry1 = String.format("Select * from usersrecords where first_name = '%s'" , F_name);

                    ResultSet resultSet = statement.executeQuery(yourQry1);

                    while(resultSet.next()) {
                        String FN = resultSet.getString("First_name");
                        String A = resultSet.getString("PersonId");
                        String B = resultSet.getString("City");
                        String C = resultSet.getString("Language");


                        System.out.println("PersonId :" + A);
                        System.out.println("Name :" + FN);
                        System.out.println("City :" + B);
                        System.out.println("Language :" + C);
                    }

                    System.out.println("Back?");

                    Scanner Qs = new Scanner(System.in);

                    String Back = Qs.next();

                    if(Back.equals("yes") || Back.equals("y"))
                    {
                        Users();
                    }
                }


                if (CH == 4) {
                    String[] d = new String[1];
                    main(d);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
