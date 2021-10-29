package login;

import java.sql.*;
import java.util.Scanner;

public class Login {
    //注册新用户，前提是有车辆
    public void SignUp(){
        Scanner sc=new Scanner(System.in);
        System.out.println("username:");
        String username=sc.next();
        System.out.println("engine_id:");
        int engine_id=sc.nextInt();

        Connection connection=null;
        Connection connection2=null;
        PreparedStatement statement=null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/homework",//
                    "common",
                    "54321"
            );
            //定义sql语句
            //创建新用户设置名字
            String sql = "insert into driver (name) values (?);";
            //获取执行sql对象
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);

            //执行sql
            statement.executeUpdate();


            //定义sql语句
            //绑定车辆
            String sql2 = "select * from car where engine_id=?;";
            //获取执行sql对象
            statement = connection.prepareStatement(sql2);
            statement.setInt(1,engine_id);

            //执行sql
            ResultSet resultSet = statement.executeQuery();
            //处理结果

            String lis="无";
            while (resultSet.next()){
                lis=resultSet.getString("license");
            }

            //设置车牌号
            String sql3="update driver set car_license = ? where name=?;";
            statement = connection.prepareStatement(sql3);

            statement.setString(1,lis);
            statement.setString(2,username);

            //执行sql
            statement.executeUpdate();

            //设置车辆车主名字
            connection2 = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/homework",//
                    "admin",
                    "12345"
            );
            String sql4="update car set driver_name = ? where engine_id=?;";
            statement = connection2.prepareStatement(sql4);

            statement.setString(1,username);
            statement.setInt(2,engine_id);

            //执行sql
            statement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    //普通用户登录
    public void CommonLogin(){
        Scanner sc=new Scanner(System.in);
        System.out.println("username:");
        String username=sc.next();


        Connection connection=null;
        PreparedStatement statement=null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/homework",
                    "common",
                    "54321"
            );
            //定义sql语句
            String sql = "select car.* from car,driver where driver.name=? and driver_name=?;";
            //获取执行sql对象
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,username);

            //执行sql
            ResultSet resultSet = statement.executeQuery();
            //处理结果
            if(resultSet.next()){
                do{
                    String violation=resultSet.getString("violation");
                    String lis=resultSet.getString("license");
                    System.out.println(lis+"  "+violation);
                }while (resultSet.next());
            }else {
                System.out.println("No record !!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    //管理员登录
    public void AdminLogin(){
        Scanner sc=new Scanner(System.in);
        System.out.println("password:");
        String password=sc.next();

        if(password.equals("12345")){
            //查询，输入想要查询的内容,如果查询所以，则输入"*"
            //new AdminUpdate().AdminQuery("license");
            //查询城市
            //new AdminUpdate().AdminQueryCity("南京");
            //修改违章信息
            new AdminUpdate().AdminUpdateVio(444444,"不系安全带");
            //添加一条记录
            //new AdminUpdate().AdminInsert(444444,"苏A54321",1,"benz");
        }else {
            System.out.println("error pw!");
        }
    }
}
