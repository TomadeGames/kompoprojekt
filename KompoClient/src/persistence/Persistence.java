/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entity.GesamtLeihe;
import entity.Leihe;
import entity.Material;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author woors
 */
public class Persistence {
    
    private static Long nextMaterialId = 1L;
    private static Long nextLeiheId = 1L;
    private static Long nextGesamtLeiheId =1L;
    private static Connection con;
    
    private static boolean buildConnection() {
        try{
         Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/kastner_oberbeck_rieke", "bla", "bla");
            con.setAutoCommit(true);
            DatabaseMetaData metadata = con.getMetaData();
            ResultSet resultSet;
            System.out.println("Suche MATERIAL Table");
            resultSet = metadata.getTables(null, null, "MATERIAL", null);
            if(!resultSet.next()){
            System.out.println("MATERIAL Table wird erstellt");
                Statement stmt = con.createStatement();
                stmt.executeUpdate("CREATE TABLE MATERIAL " +
                    "(id BIGINT not NULL, " +
                    " name VARCHAR(255), " + 
                    " anzahl INTEGER, " + 
                    " geloescht SMALLINT, " + 
                    " PRIMARY KEY ( id ))");
            }
            System.out.println("Suche LEIHE Table");
            resultSet = metadata.getTables(null, null, "LEIHE", null);
            if(!resultSet.next()){
                System.out.println("LEIHE Table wird erstellt");
                Statement stmt = con.createStatement();
                stmt.executeUpdate("CREATE TABLE LEIHE " +
                    "(id BIGINT not NULL, " +
                    " name VARCHAR(255), " + 
                    " anzahl INTEGER, " + 
                    " endedatum DATE, " + 
                    " startdatum DATE," +
                    " material_id BIGINT," +
                    " PRIMARY KEY ( id ))");
            }
            System.out.println("Suche GESAMTLEIHE Table");
            resultSet = metadata.getTables(null, null, "GESAMTLEIHE", null);
            if(!resultSet.next()){
                System.out.println("GESAMTLEIHE Table wird erstellt");
                Statement stmt = con.createStatement();
                stmt.executeUpdate("CREATE TABLE GESAMTLEIHE " +
                    "(id BIGINT not NULL, " +
                    " name VARCHAR(255), " + 
                    " PRIMARY KEY ( id ))");
            }
            System.out.println("Suche GESAMTLEIHE_LEIHE Table");
            resultSet = metadata.getTables(null, null, "GESAMTLEIHE_LEIHE", null);
            if(!resultSet.next()){
                System.out.println("GESAMTLEIHE_LEIHE Table wird erstellt");
                Statement stmt = con.createStatement();
                stmt.executeUpdate("CREATE TABLE GESAMTLEIHE_LEIHE " +
                    "(gesamtleihe_id BIGINT not NULL, " +
                    " einzelleihen_id BIGINT, " + 
                    " PRIMARY KEY ( gesamtleihe_id, einzelleihen_id))");
            }
            return true;
        }catch(Exception e){
            System.out.println("Fehler beim Verbinden: "+ e);
            return false;
        }
    }
    
    public static String insertGesamtLeihe(List<Leihe> leihen, String name){
        for(Leihe l: leihen){
            String check = checkLeihe(l);
            if(!check.equals("Leihe Erfolgreich")){
                return check;
            }
        }
        Long id = Persistence.getFreeGesamtLeiheId();
        if(con == null){
            if(!buildConnection()){
                return null;
            }
        }
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into GesamtLeihe(Id, name) values(" 
                    + id + ",'" + name + "')");
            
            System.out.println(">Erfolgreich");
            leihen.stream().forEach((l) -> {
                insertLeihe(l.getAnzahl(), l.getMaterialId(), l.getVon(), l.getBis());
            });
            return "Leihe Erfolgreich";
        }
        catch(Exception e){
            System.out.println("Fehler beim Eintragen von Gesamtleihe: " + e);
        }
        return "Ein Fehler ist aufgetreten";
    }
    
    private static String checkLeihe(Leihe b){
        System.out.println(b.toString());
        if(b.getVon() == null){
            return "Das Startdatum fehlt";
        }if(b.getBis() == null){
            return "Das Enddatum fehlt";
        }
        if(b.getAnzahl() <=0){
            return "Mindestens 1 Material muss ausgeliehen werden";
        }
        if(b.getBis().before(b.getVon())){
            return "Das Enddatum muss nach dem Startdatum sein";
        }
        //Prüfung ob Material existiert
        Material m = Persistence.getMaterial(b.getMaterialId());
        if(m == null){
            return "Material existiert nicht";
        }
        
        //Prüfung auf Vorhandene Anzahl
        List<Leihe> leihen = Persistence.getLeihen();
        int summe = 0;
        for(Leihe l: leihen){
            if(!(b.getBis().before(l.getVon()) 
                    || b.getVon().after(l.getBis()))){
                if(l.getMaterialId().equals(m.getId())){
                    summe += l.getAnzahl();
                }
            }
        }
        if(summe + b.getAnzahl() <= m.getAnzahl()){
            return "Leihe Erfolgreich";
        }
        return "So viele Artikel sind nicht verfügbar";
    }
    
    private static void insertLeihe(int anzahl, Long materialId, Date von, Date bis){
        Long id = getFreeLeiheId();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String vonString = df.format(von);
        String bisString = df.format(bis);
        if(con == null){
            if(!buildConnection()){
                return;
            }
        }
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into LEIHE(Id, anzahl, endedatum, startdatum, material_id) values(" 
                    + id + "," + anzahl + ",'" + bisString + "','" + vonString + "'," + materialId + ")");
            
            System.out.println(">Erfolgreich");
            
        }
        catch(Exception e){
            System.out.println("Fehler beim Eintragen von Leihe: " + e);
        }
    }
    
    public static void insertMaterial(String materialName, int anzahl) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
       if(con == null){
           if(!buildConnection()){
               return;
           }
       }
        try{
            Long id = getFreeMaterialId();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into MATERIAL(id, anzahl, geloescht, name) values(" 
                    + id + ", " + anzahl + ", 0, '" + materialName + "')");
            
            System.out.println(">Erfolgreich");
            
        }
        catch(Exception e){
            System.out.println("Fehler beim Eintragen von Material: " + e);
        }
    }
        
    private static Long getFreeMaterialId(){
        List<Material> mats = Persistence.getMaterial();
        boolean erfolgreich;
        do{
            erfolgreich = true;
            System.out.println("NextId: " + nextMaterialId);
            for(Material m: mats){
                if(m.getId().equals(nextMaterialId)){
                    nextMaterialId++;
                    erfolgreich = false;
                }
            }
        }while(!erfolgreich);
        return nextMaterialId;
    }
    
    public static Long getFreeLeiheId(){
        List<Leihe> leihen = Persistence.getLeihen();
        boolean erfolgreich;
        do{
            erfolgreich = true;
            System.out.println("NextId: " + nextLeiheId);
            for(Leihe l: leihen){
                if(l.getId().equals(nextLeiheId)){
                    nextLeiheId++;
                    erfolgreich = false;
                }
            }
        }while(!erfolgreich);
        return nextLeiheId;
    }
    
    public static Long getFreeGesamtLeiheId(){
        List<GesamtLeihe> leihen = Persistence.getGesamtLeihen();
        boolean erfolgreich;
        do{
            erfolgreich = true;
            System.out.println("NextId: " + nextGesamtLeiheId);
            for(GesamtLeihe l: leihen){
                if(l.getId().equals(nextGesamtLeiheId)){
                    nextGesamtLeiheId++;
                    erfolgreich = false;
                }
            }
        }while(!erfolgreich);
        return nextGesamtLeiheId;
    }
    
    public static List<GesamtLeihe> getGesamtLeihen(){
        List<GesamtLeihe> erg = new ArrayList<>();
        if(con == null){
           if(!buildConnection()){
               return null;
           }
       }
        try{
            Statement stmt = con.createStatement();
            System.out.println("Select Material");
            ResultSet rs = stmt.executeQuery("select * from GesamtLeihe");
            System.out.println(">Erfolgreich");
            while(rs.next()){
                GesamtLeihe l = new GesamtLeihe();
                l.setId(rs.getLong("Id"));
                l.setName(rs.getString("Name"));
                erg.add(l);
            }
        }
        catch(Exception e){
            System.out.println("Fehelr bei getGesamtLeihen" + e);
        }
        return erg;
    }
    
    public static List<Leihe> getLeihen(){
        List<Leihe> erg = new ArrayList<>();
        if(con == null){
           if(!buildConnection()){
               return null;
           }
       }
        try{
            Statement stmt = con.createStatement();
            System.out.println("Select Leihe");
            ResultSet rs = stmt.executeQuery("select * from Leihe");
            System.out.println(">Erfolgreich");
            
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            while(rs.next()){
                String vonString = rs.getString("Startdatum");
                String endString = rs.getString("Endedatum");
                java.util.Date vonUtilDate = df.parse(vonString);
                java.util.Date bisUtilDate = df.parse(endString);
                Date von = new Date(vonUtilDate.getTime());
                Date bis = new Date(bisUtilDate.getTime());
                Leihe l = new Leihe();
                l.setId(rs.getLong("Id"));
                l.setVon(von);
                l.setBis(bis);
                l.setAnzahl(rs.getInt("Anzahl"));
                l.setMaterialId(rs.getLong("Material_Id"));
                erg.add(l);
            }
        }
        catch(Exception e){
            System.out.println("Fehelr bei getLeihen" + e);
        }
        return erg;
    }
    
    public static List<Material> getMaterial(){
        List<Material> erg = new ArrayList<>();
        if(con == null){
           if(!buildConnection()){
               return null;
           }
       }
        try{
            Statement stmt = con.createStatement();
            System.out.println("Select Material");
            ResultSet rs = stmt.executeQuery("select * from Material");
            System.out.println(">Erfolgreich");
            while(rs.next()){
                Material mat = new Material();
                mat.setId(rs.getLong("Id"));
                mat.setName(rs.getString("Name"));
                mat.setAnzahl(rs.getInt("Anzahl"));
                erg.add(mat);
            }
        }
        catch(Exception e){
            System.out.println("Fehelr bei getMaterial()" + e);
        }
        return erg;
    }
    
    public static Material getMaterial(Long id){
        if(con == null){
           if(!buildConnection()){
               return null;
           }
       }
        try{
            Statement stmt = con.createStatement();
            System.out.println("Select Material");
            ResultSet rs = stmt.executeQuery("select * from Material where id = " + id);
            System.out.println(">Erfolgreich");
            while(rs.next()){
                Material mat = new Material();
                mat.setId(rs.getLong("Id"));
                mat.setName(rs.getString("Name"));
                mat.setAnzahl(rs.getInt("Anzahl"));
                return mat;
            }
        }
        catch(Exception e){
            System.out.println("Fehelr bei getMaterial(Long)" + e);
        }
        return null;
    }
    
    public static Material getMaterial(String name){
        if(con == null){
           if(!buildConnection()){
               return null;
           }
       }
        try{
            Statement stmt = con.createStatement();
            System.out.println("Select Material");
            ResultSet rs = stmt.executeQuery("select * from Material where name = '" + name + "'");
            System.out.println(">Erfolgreich");
            while(rs.next()){
                Material mat = new Material();
                mat.setId(rs.getLong("Id"));
                mat.setName(rs.getString("Name"));
                mat.setAnzahl(rs.getInt("Anzahl"));
                return mat;
            }
        }
        catch(Exception e){
            System.out.println("Fehelr bei getMaterial(String)" + e);
        }
        return null;
    }
}
