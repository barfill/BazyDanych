import java.sql.*;
import java.util.Scanner;

public class SimpleJDBCExample {
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException {
        String conString = "jdbc:postgresql://localhost:5432/appdb";

        Class.forName("org.postgresql.Driver").newInstance();

        Connection con = DriverManager.getConnection(conString,"dbuser","dbuser");
        //Statement stm = con.createStatement();
        //stm.execute("SELECT * FROM PRACOWNIK");

        //wypisać pracowników w formie: ID: WARTOŚĆ     IMIE: WARTOŚĆ   STANOWISKO: NAZWA
        //Podajemy ID jednostki i wypisujemy pracowników w powyższym formacie

        //PreparedStatement pstm = con.prepareStatement("SELECT * FROM PRACOWNIK WHERE id>?");
        //pstm.setInt(1,3);
        //System.out.println(pstm);

        //PreparedStatement pstm = con.prepareStatement("SELECT * FROM PRACOWNIK JOIN STANOWISKO ON PRACOWNIK.STANOWISKO = STANOWISKO.ID"); //pierwsze
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM PRACOWNIK JOIN STANOWISKO ON PRACOWNIK.STANOWISKO = STANOWISKO.ID WHERE PRACOWNIK.id=?");

        System.out.print("Podaj ID pracownika: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        pstm.setInt(1,id);
        pstm.executeQuery();

        //ResultSet rs = stm.getResultSet();
        ResultSet rs = pstm.getResultSet();
        while (rs.next()){
            /*
            System.out.print(rs.getInt(1));
            System.out.print(" ");
            System.out.print(rs.getString("imie"));
            System.out.print(" ");
            System.out.println(rs.getString("nazwisko"));
             */
            System.out.println("ID:"+rs.getString(1)+"  IMIE:"+rs.getString("imie")+"   STANOWISKO:"+rs.getString("nazwa"));

        }
        rs.close();
        //stm.close();
        pstm.close();
        con.close();
    }
}