import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author begench
 */
public class CalisanIslemleri {
    private Connection con = null;
    
    private Statement statement = null;
    
    private PreparedStatement preparedStatement = null;
    
    public ArrayList<Calisan> calisanlariGetir(){
        
    ArrayList<Calisan> cikti = new ArrayList<Calisan>();
    
        try {
            statement = con.createStatement();
            String sorgu = "Select * From calisanlar";
            
            ResultSet rs = statement.executeQuery(sorgu);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String dept = rs.getString("departman");
                String maas = rs.getString("maas");
                
                cikti.add(new Calisan(id,ad,soyad,dept,maas));
                  
            }
            return cikti;
            
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    
    
}
    
    
    public Boolean girisYap(String kullanici_adi , String parola){
        
        String sorgu = "Select * From adminler where username = ? and password = ?";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setString(1,kullanici_adi);
            preparedStatement.setString(2, parola);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            return rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(CalisanIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    public CalisanIslemleri(){
        
        String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.db_ismi;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Driver Bulunamadi...");
        }
        try{
            con = DriverManager.getConnection(url,Database.kullanici_adi,Database.parola);
            System.out.println("sql.Baglanti Basarili...");
        }
        catch(SQLException e){
            System.out.println("sql.Baglanti Basarisiz...");
        }
    }
    
}
