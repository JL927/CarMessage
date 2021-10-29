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

    public void AdminQueryCity(String s){
        Connection con=null;
        PreparedStatement sta=null;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            con= DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/homework",
                    "admin",
                    "12345"
            );
            String sql="select * from city where city_name=?;";
            sta=con.prepareStatement(sql);
            sta.setString(1,s);

            ResultSet resultSet=sta.executeQuery();
            int i=0;
            if(resultSet.next()){
               i=resultSet.getInt("city_id");
            }else{
                System.out.println("No record !!");
            }


            String sql2="select * from car where cityid=?;";
            sta=con.prepareStatement(sql2);
            sta.setString(1,i+"");

            resultSet = sta.executeQuery();
            if(resultSet.next()){
                do{
                    String name=resultSet.getString("driver_name");
                    String violation=resultSet.getString("violation");
                    String lis=resultSet.getString("license");
                    System.out.println(name+"  "+lis+"  "+violation);
                }while (resultSet.next());
            }else{
                System.out.println("No record !!");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void AdminUpdateVio(int id,String vio){
        Connection con=null;
        PreparedStatement sta=null;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            con= DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/homework",
                    "admin",
                    "12345"
            );
            String sql="update car set violation=? where engine_id=?;";
            sta=con.prepareStatement(sql);
            sta.setString(1,vio);
            sta.setString(2,id+"");

            sta.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void AdminInsert(int engine_id,String lis,int city_id,String model){
        Connection con;
        PreparedStatement sta;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            con= DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/homework",
                    "admin",
                    "12345"
            );

            String sql="insert into car (engine_id, license, cityid, model) VALUES (?,?,?,?);";
            sta=con.prepareStatement(sql);
            sta.setString(1,engine_id+"");
            sta.setString(2,lis);
            sta.setString(3,city_id+"");
            sta.setString(4,model);

            sta.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
