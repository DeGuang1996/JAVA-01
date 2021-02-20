package question2.jdbc;

import java.sql.*;
import java.util.List;

/**
 * @author deguang
 * @date 2021/02/21
 */

public class JDBCOperation {

    private static Connection getCon() throws Exception{
        String url = "jdbc:mysql://localhost:3306/student";
        String user = "root";           //用户名
        String password = "1234";     //密码
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //建立连接
        Connection con = DriverManager.getConnection(url, user, password);

        return con;
    }



    static void insert(Student student) throws Exception {
        Connection conn = getCon();
        String sql = "insert into Student (Sno,Sname,Ssex,Sbirthday,Class) values(?,?,?,?,?)";
        PreparedStatement pstmt;

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, student.getSno());
        pstmt.setString(2, student.getSname());
        pstmt.setString(3, student.getSsex());
        pstmt.setString(4, student.getSbirthday());
        pstmt.setString(5, student.getSclass());
        pstmt.executeUpdate();     //执行数据库语句
        pstmt.close();
        conn.close();
    }

    static void batchInsert() throws Exception {
        Connection conn = getCon();
        String sql = "insert into Student(Sno, Sname) values(?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            int index = 1 ;
            pstmt.setString(1, "aa"+i);
            pstmt.setString(2, "aa"+i);
            pstmt.addBatch();
        }
        int[] executeBatch = pstmt.executeBatch();
        for (int i = 0; i < executeBatch.length; i++) {
            System.out.println(i + "ok");
        }
    }

    static void transaction() throws Exception {
        Connection conn = getCon();
        try {
            conn.setAutoCommit(false);
            insert(new Student());
            conn.commit();
        } catch (SQLException e) {
            // 回滚事务:
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    static void delete(String Sno) throws Exception{
        Connection con = getCon();
        String sql = "delete from Student where Sno = '" + Sno + "'";
        PreparedStatement pstmt = con.prepareStatement(sql);
        con.close();
        pstmt.close();
    }

    static void update(Student student) throws Exception {
        Connection conn = getCon();
        String sql = "update Student set Sname = ?, Ssex = ?, Sbirthday = ?, Class = ? where Sno = ?";
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, student.getSname());
        pstmt.setString(2, student.getSsex());
        pstmt.setString(3, student.getSbirthday());
        pstmt.setString(4, student.getSclass());
        pstmt.setString(5, student.getSno());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    static Integer getAll() throws Exception {
        Connection conn = getCon();
        String sql = "select * from Student";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Student> list = new ArrayList<Student>(); //使用集合存储结果

        while(rs.next()){     //将每一条查询到的结果存入Student对象
            Student student = new Student();
            student.setSno(rs.getString(1));
            student.setSname(rs.getString(2));
            student.setSsex(rs.getString(3));
            student.setSbirthday(rs.getString(4));
            student.setSclass(rs.getString(5));
            list.add(student);
        }

        for(Student student : list){
            System.out.print(student.getSno() + "\t");
            System.out.print(student.getSname() + "\t");
            System.out.print(student.getSsex() + "\t");
            System.out.print(student.getSbirthday() + "\t");
            System.out.print(student.getSclass() + "\t");
            System.out.println();
        }
        System.out.println();
        return null;
    }

}
