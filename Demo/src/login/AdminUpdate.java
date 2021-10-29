package login;

import java.sql.*;

public class AdminUpdate {
    public void AdminQuery(String s){
        Connection con=null;
        Statement sta=null;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            con= DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/homework",
                    "admin",
                    "12345"
            );
            String sql="select * from car;";
            sta=con.createStatement();

            ResultSet resultSet = sta.executeQuery(sql);
            if(resultSet.next()){
                if(s.equals("*")){
                    do{
                        String violation=resultSet.getString("violation");
                        String lis=resultSet.getString("license");
                        String nm=resultSet.getString("driver_name");
                        System.out.println(nm+"  "+lis+"  "+violation);
                    }while (resultSet.next());
                }else {
                    do{
                        String result=resultSet.getString(s);
                        System.out.println(result);
                    }while (resultSet.next());
                }

            }else{
                    System.out.println("No record !!");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
