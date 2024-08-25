import java.sql.*;

public class Main {

    public static void main(String[] args) {

        /*
            1.import package
            2.load and register
            3.create Connection
            4.Create Statement
            5.Execute Statement
            6.Process the results
            7.Close th Connection
        */


        String url = "jdbc:postgresql://localhost:5432/Demo";
        String username = "postgres";
        String password = "yogesh@39";

        int u_id = 72;
        String U_name= "PandurangBhau";
        int u_marks = 75;

        try{
        Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e);
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Success!!");
            Statement statement = connection.createStatement();
            String query = "select * from student";
            String query2 = "insert into student values(4, 'Shivam', 78) ";    //insert command
            String query3 = "update student set s_name = 'Kartik' where s_marks = 78";   //Update command
            String query4 = "delete from student where s_id=4";    //Delete command
            String query5 = "insert into student values("+ u_id +",'"+ U_name +"',"+ u_marks +")";
            String prep_query = "insert into student values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(prep_query);
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("S_id | S_name | S_marks");
            while(resultSet.next()){
//                String name = resultSet.getString("s_name");
//                String id = resultSet.getString("s_id");
//                String marks = resultSet.getString("s_marks");
//                System.out.println("Student ID: " + id + " Name: " +name + " Marks: " + marks);

                System.out.print(resultSet.getInt("s_id") + " | ");
                System.out.print(resultSet.getString("s_name") + " | ");
                System.out.println(resultSet.getInt("s_marks"));
            }
            //statement.execute(query2);     //Insert Command
            //statement.execute(query3);     //Update command
            //statement.execute(query4);       //Delete command
            statement.execute(query5);

            //Prepared Statement
            preparedStatement.setInt(1, 34);
            preparedStatement.setString(2, "Ram");
            preparedStatement.setInt(3, 67);
            preparedStatement.execute(); // prepared Statement
            connection.close();
            System.out.println("Connection Closed");
        }catch(SQLException e){
            System.out.println(e);
        }


    }
}