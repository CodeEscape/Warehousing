import java.sql.*;
import java.util.ArrayList;

public class Database {

    private DobavnicaPanel dobavnicaPanel;

    private Connection povezava;
    private Statement stmt;

    public Database(){

        try {
            Class.forName("org.sqlite.JDBC");
            povezava = DriverManager.getConnection("jdbc:sqlite:DBProizvodnje.db");

            String sqlUkaz0 = "CREATE TABLE IF NOT EXISTS Skladisca " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "imeSkladisca CHAR(30))";

            //String imeSkladisca = String.valueOf(formPanelSZ.getSkladisceBox());

            //String sqlInsertSkladisce = "INSERT INTO Skladisce(imeSkladisca)" + "VALUES ('Glavno')";

            String sqlUkaz1 = "CREATE TABLE IF NOT EXISTS Uporabniki " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "imeUporabnika CHAR(30));";

            String sqlUkaz2 = "CREATE TABLE IF NOT EXISTS Prevzemnica " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "datumPrevzemnice TEXT, " +
                    "stevilkaPrevzemnice INT, " +
                    "idKupca INT, " +
                    "FOREIGN KEY (idKupca) REFERENCES Kupci (id))";

            String sqlUkaz3 = "CREATE TABLE IF NOT EXISTS Izdelki " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "imeIzdelka CHAR(30), " +
                    "cena INT, " +
                    "sifraIzdelka INT)";

            String sqlUkaz4 = "CREATE TABLE IF NOT EXISTS Dobavitelji " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "imeDobavitelja CHAR(30))";

            String sqlUkaz5 = "CREATE TABLE IF NOT EXISTS Kupci " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "imeKupca CHAR(30))";
            /*
            String sqlUkaz6 = "CREATE TABLE IF NOT EXISTS PoslovnaEnota " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "poslovnaEnota CHAR(30))";
            */
            String sqlUkaz7 = "CREATE TABLE IF NOT EXISTS IzdelkiVSkladiscu " +
                    "(idSkladisca INT, " +
                    "idIzdelka INT, " +
                    "zaloga INT, " +
                    "FOREIGN KEY (idSkladisca) REFERENCES Skladisca (id) " +
                    "FOREIGN KEY (idIzdelka) REFERENCES Izdelki (id))";

            String sqlUkaz8 = "CREATE TABLE IF NOT EXISTS IzdelkiVPrevzemnici " +
                    "(idPrevzemnice INT, " +
                    "idIzdelka INT, " +
                    "FOREIGN KEY (idPrevzemnice) REFERENCES Prevzemnica (id) " +
                    "FOREIGN KEY (idIzdelka) REFERENCES Izdelki (id))";

            String sqlUkaz9 = "CREATE TABLE IF NOT EXISTS Dobavnica " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "datumDobavnice TEXT, " +
                    "datumDostave TEXT, " +
                    "stevilkaDobavnice INT, " +
                    "idSkladisca INT, " +
                    "idDobavitelja INT, " +
                    "idUporabnika INT, " +
                    "FOREIGN KEY (idSkladisca) REFERENCES Skladisca (id) " +
                    "FOREIGN KEY (idDobavitelja) REFERENCES Dobavitelji (id) " +
                    "FOREIGN KEY (idUporabnika) REFERENCES Uporabniki (id))";

            String sqlUkaz10 = "CREATE TABLE IF NOT EXISTS IzdelkiVDobavnici " +
                    "(idDobavnice INT, " +
                    "idIzdelka INT, " +
                    "FOREIGN KEY (idDobavnice) REFERENCES Dobavnica (id) " +
                    "FOREIGN KEY (idIzdelka) REFERENCES Izdelki (id))";

            //String sqlUkazDelete = "DELETE Prevzemnica";
            //String sqlUkazInsert = "ALTER TABLE Prevzemnica ADD COLUMN idSkladisca;";

            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlUkaz0);
            stmt.executeUpdate(sqlUkaz1);
            stmt.executeUpdate(sqlUkaz2);
            stmt.executeUpdate(sqlUkaz3);
            stmt.executeUpdate(sqlUkaz4);
            stmt.executeUpdate(sqlUkaz5);
            //stmt.executeUpdate(sqlUkaz6);
            stmt.executeUpdate(sqlUkaz7);
            stmt.executeUpdate(sqlUkaz8);
            stmt.executeUpdate(sqlUkaz9);
            stmt.executeUpdate(sqlUkaz10);

            //stmt.executeUpdate(sqlUkazDelete);
            //stmt.executeUpdate(sqlUkazInsert);

            //stmt.close();
            //System.out.println("Povezava s SQLite vzpostavljena");

        }catch(Exception e) {
            System.out.println("Prišlo je do napake: " + e.getMessage());
        }
    }

    public void disconect(){//to se mora zgodit preden se program ugasne
        try {
            povezava.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
///////////// Dodajanje v bazo   ///////////////
    public void dodajUporabnika(String upIme){
        String sqlInsertUporabnik = "INSERT INTO Uporabniki (ImeUporabnika) VALUES ('" + upIme + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertUporabnik);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void spremembaBoxa(String box, String vrednost, int id){
        String colName = "";
        switch (box){
            case "dobaviteljBox":
                colName = "ime";
                break;
            case "krajBox":
                colName = "KrajDobavitelja";
                break;
        }
        String updateBox = "UPDATE Dobavitelji SET" + colName + " = " + vrednost + " WHERE idDobavitelji = " + id + ";";
    }

    public void dodajDobavitelja(int sifraIzdelka, String ime){
        String sqlInsertDobavitelj = "INSERT INTO Dobavitelji (sifraIzdelka, imeDobavitelja) VALUES (" + sifraIzdelka + ", '" + ime + "');";
        try {
            //stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertDobavitelj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajIzdelek(String imeIzdelka, int sifraIzdelka) {
        String sqlInsertIzdelek = "INSERT INTO Izdelki (imeIzdelka, sifraIzdelka) VALUES ('" + imeIzdelka + "', '" + sifraIzdelka + "'); ";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertIzdelek);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajIzdelek(int sifraIzdelka) {
        String sqlInsertIzdelek = "INSERT INTO Izdelki (sifraIzdelka) VALUES ('" + sifraIzdelka + "'); ";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertIzdelek);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajIzdelek(String imeIzdelka) {
        String sqlInsertIzdelek = "INSERT INTO Izdelki (imeIzdelka) VALUES ('" + imeIzdelka + "'); ";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertIzdelek);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    public void dodajIzdelek2(String imeIzdelka, int kolicina) {
        String sqlInsertIzdelek = "INSERT INTO Izdelki (imeIzdelka, kolicina, sifraIzdelka) VALUES ('" + imeIzdelka + "', " + kolicina + "); ";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertIzdelek);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
/*
    public void dodajNarocila(String poslovnaEnota, String nabavnik,  int datumNarocila, int datumDostave, int cena) {
        String sqlInsertNarocilo = "INSERT INTO Narocila (poslovnaEnota, nabavnik, datumNarocila, datumDostave, cena) " +
                "VALUES ('" + poslovnaEnota + "', '" + nabavnik + "', '" + datumNarocila + "', '" + datumDostave + "', " + cena + ");";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertNarocilo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajDobavnico(String datumDostave, String datumDobavnice, int stevilkaDobavnice) {
        String sqlInserDobavnico = "INSERT INTO Dobavnica (datumDostave, datumDobavnice, stevilkaDobavnice) " +
                "VALUES ('" + datumDostave + "', '" + datumDobavnice + "', " + stevilkaDobavnice + ");";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInserDobavnico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    public void dodajSkladisce(String imeSkladisca, int zaloga) {
        String sqlInsertSkladisce = "INSERT INTO Skladisca (imeSkladisca, zaloga) VALUES ('" + imeSkladisca + "', " + zaloga + ");";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertSkladisce);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getImeDobaviteljev() {
        ArrayList<String> imeDobavitelji= new ArrayList<String>();
        String sqlLoadImeDobavitelj = "SELECT imeDobavitelja FROM Dobavitelji"; //where active = true
        ResultSet results = null;
        try {
            results = stmt.executeQuery(sqlLoadImeDobavitelj);
            while(results.next()){
                imeDobavitelji.add(results.getString("imeDobavitelja"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imeDobavitelji;
    }

    public void dolzinaStolpca(String imeTabele, int stStolpca){

    }
    ////////////////// Šteje, če je vnos že v bazi  ///////////////
    public int countUporabnik(String uporabnik) {
        int count = 0;
        String sqlIsciUporabnika = "SELECT COUNT(*) FROM Uporabniki WHERE imeUporabnika = '" + uporabnik + "'";
        try {
            ArrayList<String> rezultati = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery(sqlIsciUporabnika);

            //Loopam čez use rezultate
            while (rs.next()) {
                rezultati.add(rs.getString(1));
            }
            //Error v queryu
            if(rezultati.size() == 0){
                throw new Exception("Napačen query");
            }

            if(rezultati.size() == 1){
                count = Integer.parseInt(rezultati.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int countJeVBaziInt(int vnos, String tabela, String stolpec) {
        int count = 0;
        String sqlIsciVnos = "SELECT COUNT(*) FROM " + tabela + " WHERE " + stolpec + " = '" + vnos + "'";
        try {
            ArrayList<String> rezultati = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery(sqlIsciVnos);

            //Loopam čez use rezultate
            while (rs.next()) {
                rezultati.add(rs.getString(1));
            }
            //Error v queryu
            if(rezultati.size() == 0){
                throw new Exception("Napačen query");
            }

            if(rezultati.size() == 1){
                count = Integer.parseInt(rezultati.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int countJeVBaziString(String vnos, String tabela, String stolpec) {
        int count = 0;
        String sqlIsciVnos = "SELECT COUNT(*) FROM " + tabela + " WHERE " + stolpec + " = '" + vnos + "'";
        System.out.println(sqlIsciVnos);
        try {
            ArrayList<String> rezultati = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery(sqlIsciVnos);

            //Loopam čez use rezultate
            while (rs.next()) {
                rezultati.add(rs.getString(1));
            }
            //Error v queryu
            if(rezultati.size() == 0){
                throw new Exception("Napačen query");
            }

            if(rezultati.size() == 1){
                count = Integer.parseInt(rezultati.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean uporabnikObstaja(String text) {
        if (countUporabnik(text) == 1) {
            return true;
        } else {
            return false;
        }
    }
/////////////////  Zbere vse vrednosti v bazi za JComboBox //////////////////////////
    public ArrayList<String> getStevilkaDobavnice() {
        ArrayList<String> stevilkaDobavnice= new ArrayList<String>();
        String sqlLoadStDobavnice = "SELECT stevilkaDobavnice FROM Dobavnica";
        ResultSet results = null;
        try {
            results = stmt.executeQuery(sqlLoadStDobavnice);
            while(results.next()){
                stevilkaDobavnice.add(results.getString("stevilkaDobavnice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stevilkaDobavnice;
    }

/*  ////// Testiram, ker mi daje nullpointerexception
    public int getDobavnica(){
        String sqlLoadStDobavnice = "SELECT stevilkaDobavnice FROM Dobavnica";
        int stDob = 0;
        try{
            ResultSet rs = stmt.executeQuery(sqlLoadStDobavnice);
            while (rs.next()) {
                System.out.println(rs.getInt("stevilkaDobavnice"));
                stDob = (rs.getInt("stevilkaDobavnice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stDob;
    }
*/
    public ArrayList<String> getStevilkaPrevzemnice() {
        ArrayList<String> stevilkaPrevzemnice= new ArrayList<String>();
        String sqlLoadStPrevzemnice = "SELECT stevilkaPrevzemnice FROM Prevzemnica";
        ResultSet results = null;
        try {
            results = stmt.executeQuery(sqlLoadStPrevzemnice);
            while(results.next()){
                stevilkaPrevzemnice.add(results.getString("stevilkaPrevzemnice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stevilkaPrevzemnice;
    }

    public ArrayList<String> getImeSkladisca() {
        ArrayList<String> imeSkladisca = new ArrayList<String>();
        String sqlLoadImeSkladisca = "SELECT imeSkladisca FROM Skladisca";
        ResultSet results = null;
        try {
            results = stmt.executeQuery(sqlLoadImeSkladisca);
            while(results.next()){
                imeSkladisca.add(results.getString("imeSkladisca"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imeSkladisca;
    }

    public ArrayList<String> getSifraIzdelka() {
        ArrayList<String> sifraIzdelka = new ArrayList<String>();
        String sqlLoadSifraIzdelka = "SELECT sifraIzdelka FROM Izdelki";
        ResultSet results = null;
        try {
            results = stmt.executeQuery(sqlLoadSifraIzdelka);
            while(results.next()){
                sifraIzdelka.add(results.getString("sifraIzdelka"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sifraIzdelka;
    }

    public ArrayList<String> getImeIzdelka() {
        ArrayList<String> imeIzdelka = new ArrayList<String>();
        String sqlLoadImeIzdelka = "SELECT imeIzdelka FROM Izdelki";
        ResultSet results = null;
        try {
            results = stmt.executeQuery(sqlLoadImeIzdelka);
            while(results.next()){
                imeIzdelka.add(results.getString("imeIzdelka"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imeIzdelka;
    }

    public ArrayList<String> getTrZaloga() {
        ArrayList<String> trZaloga = new ArrayList<String>();
        String sqlLoadTrZalogaa = "SELECT kolicina FROM Izdelki";
        ResultSet results = null;
        try {
            results = stmt.executeQuery(sqlLoadTrZalogaa);
            while(results.next()){
                trZaloga.add(results.getString("kolicina"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trZaloga;
    }

    public ArrayList<String> getCena() {
        ArrayList<String> cena = new ArrayList<String>();
        String sqlLoadTrZalogaa = "SELECT cena FROM Izdelki";
        ResultSet results = null;
        try {
            results = stmt.executeQuery(sqlLoadTrZalogaa);
            while(results.next()){
                cena.add(results.getString("cena"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cena;
    }

    public ArrayList<String> getPoslovnaEnota() {
        ArrayList<String> poslovnaEnota = new ArrayList<String>();
        String sqlLoadPoslovnaEnota = "SELECT poslovnaEnota FROM PoslovnaEnota";
        ResultSet results = null;
        try {
            results = stmt.executeQuery(sqlLoadPoslovnaEnota);
            while(results.next()){
                poslovnaEnota.add(results.getString("poslovnaEnota"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return poslovnaEnota;
    }

    public ArrayList<String> getNabavnik() {
        ArrayList<String> nabavnik = new ArrayList<String>();
        String sqlLoadNabavnik = "SELECT imeUporabnika FROM Uporabniki";
        ResultSet results = null;
        try {
            results = stmt.executeQuery(sqlLoadNabavnik);
            while(results.next()){
                nabavnik.add(results.getString("imeUporabnika"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nabavnik;
    }

    public ArrayList<String> getKupec() {
        ArrayList<String> kupec = new ArrayList<String>();
        String sqlLoadKupec = "SELECT imeKupca FROM Kupci";
        ResultSet results = null;
        try {
            results = stmt.executeQuery(sqlLoadKupec);
            while(results.next()){
                kupec.add(results.getString("imeKupca"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kupec;
    }
/*
    public void dodajPoslovnoenoto(String poslovnaEnota) {
        String sqlInsertPoslovnoEnoto = "INSERT INTO Narocila (poslovnaEnota) VALUES ('" + poslovnaEnota + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertPoslovnoEnoto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajNabavnika(String nabavnik) {
        String sqlInsertNabavnik = "INSERT INTO Narocila (nabavnik) VALUES ('" + nabavnik + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertNabavnik);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajDatumNarocila(int datumNarocila) {
        String sqlInsertDatumNarocila = "INSERT INTO Narocila (datumNarocila) VALUES (" + datumNarocila + ");";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertDatumNarocila);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajCeno(int cena) {
        String sqlInsertCena = "INSERT INTO Narocila (cena) VALUES (" + cena + ");";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertCena);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajStevilkoDobavnice(int stevilkaDobavnice) {
        String sqlInsertStDobavnice = "INSERT INTO Narocila (stevilkaDobavnice) VALUES (" + stevilkaDobavnice + ");";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertStDobavnice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajStevilkoPrevzemnice(int stevilkaPrevzemnice) {
        String sqlInsertStPrevzemnice = "INSERT INTO Narocila (stevilkaPrevzemnice) VALUES (" + stevilkaPrevzemnice + ");";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertStPrevzemnice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    public void dodajImeSkladisca(String imeSkladisca) {
        String sqlInsertSkladisce = "INSERT INTO Skladisca (imeSkladisca) VALUES ('" + imeSkladisca + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertSkladisce);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajZalogo(int zaloga) {
        String sqlInsertZaloga = "INSERT INTO Skladisca (zaloga) VALUES (" + zaloga + ");";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertZaloga);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajImeIzdelka(String imeIzdelka) {
        String sqlInsertImeIzdelka = "INSERT INTO Izdelki (imeIzdelka) VALUES ('" + imeIzdelka + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertImeIzdelka);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajKolicino(int zaloga, String skladisca, String izdelek) {
        String sqlInsertKolicina = "INSERT INTO IzdelkiVSkladiscu (idSkladisca, idIzdelka, zaloga) " +
                "VALUES " +
                "((SELECT id FROM Skladisca " +
                "WHERE imeSkladisca = '" + skladisca + "'), " +
                "(SELECT id FROM Izdelki " +
                "WHERE imeIzdelka = '" + izdelek + "'), " +
                "'" + zaloga + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertKolicina);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajSifroIzdelka(int sifraIzdelka) {
        String sqlInsertSifraIzdelka = "INSERT INTO Izdelki (sifraIzdelka) VALUES (" + sifraIzdelka + ");";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertSifraIzdelka);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajImeDobavitelja(String imeDobavitelja) {
        String sqlInsertDobavitelj = "INSERT INTO Dobavitelji (imeDobavitelja) VALUES ('" + imeDobavitelja + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertDobavitelj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajKupca(String imeKupca) {
        String sqlInsertKupec = "INSERT INTO Kupci (imeKupca) VALUES ('" + imeKupca + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertKupec);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    public void dodajDatumDostave(String datumDostave) {
        String sqlInsertDatumDostave = "INSERT INTO Narocila (datumDostave) VALUES ('" + datumDostave + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertDatumDostave);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajDatumDobavnice(int datumDobavnice) {
        String sqlInsertDatumDobavnice = "INSERT INTO Dobavnica (datumDobavnice) VALUES (" + datumDobavnice + ");";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertDatumDobavnice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public void dodajDatumPrevzema(Date datumPrevzema) {
        String sqlInsertDatumPrevzema = "INSERT INTO Narocila (datumPrevzema) VALUES (" + datumPrevzema + ");";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertDatumPrevzema);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    public int kolicinaIzdelkov(String izdelek){
        String sqlSelectKolicina = "SELECT zaloga FROM IzdelkiVPrevzemnici WHERE imeIzdelka = '" + izdelek + "';";
        try {
            stmt = povezava.createStatement();
            stmt.executeQuery(sqlSelectKolicina);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int kolicina = Integer.parseInt(sqlSelectKolicina);
        return kolicina;
    }

    public int zalogaIzdelka(String skladisca, String izdelek){
        String sqlSelectZaloga = "select ivs.zaloga from IzdelkiVSkladiscu as ivs " +
                "where ivs.idSkladisca in (select s.id from Skladisca as s " +
                "where s.imeSkladisca = '" + skladisca + "') " +
                "and ivs.idIzdelka in (select i.id from Izdelki as i " +
                "where i.imeIzdelka = '" + izdelek + "');";
        ResultSet result = null;
        int kolicina = 0;
        String zaloga = "0";
        try {
            stmt = povezava.createStatement();
            result = stmt.executeQuery(sqlSelectZaloga);
            if (result.next()) {
                zaloga = result.getString("zaloga");
            }
            kolicina = Integer.parseInt(zaloga);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(kolicina);
        return kolicina;
    }
/*
    public String izpisNarocila(int stNarocila){
        String sqlIzpisNarocila = "SELECT id_Narocila FROM Narocila WHERE id_Narocila = '" + stNarocila + "';";
        String narocilo = null;
        try {
            stmt = povezava.createStatement();
            stmt.executeQuery(sqlIzpisNarocila);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return narocilo;
    }
*/
    public void spremeniKolicino(String imeIzdelka, String imeSkladisca, int novaZaloga){
        String sqlSpremembaZaloge = "UPDATE IzdelkiVSkladiscu " +
                "SET zaloga = '" + novaZaloga + "'" +
                "WHERE idIzdelka in ( SELECT i.id FROM Izdelki as i " +
                "WHERE i.imeIzdelka = '" + imeIzdelka + "') " +
                "AND idSkladisca in (SELECT s.id FROM Skladisca as s " +
                "WHERE s.imeSkladisca = '" + imeSkladisca + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlSpremembaZaloge);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void vstaviIzdelek(String imeIzdelka, int sifraIzdelka, int cena){
        String sqlVstaviIzdelek = "INSERT INTO Izdelki (imeIzdelka, sifraIzdelka, cena) VALUES ('" + imeIzdelka + "', '" + sifraIzdelka + "', '" + cena + "'); ";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlVstaviIzdelek);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int steviloDobavnice(){
        String sqlStDobavnice = "SELECT COUNT(*) id FROM Dobavnica ";
        int count = 0;
        try {
            stmt = povezava.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStDobavnice);
            count = Integer.parseInt(rs.getObject(1).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count + 1;
    }

    public int steviloPrevzemnice(){
        String sqlStPrevzemnice = "SELECT COUNT(*) id FROM Prevzemnica ";
        int count = 0;
        try {
            stmt = povezava.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStPrevzemnice);
            count = Integer.parseInt(rs.getObject(1).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count + 1;
    }

    public int tujId( String imeTabele, String imeVrste, String imePolja){
        String sqlIzpisIdIme = "SELECT id FROM " + imeTabele + " WHERE " + imeVrste + " = '" + imePolja + "'";
        int stevilo = 0;
        try {
            stmt = povezava.createStatement();
            ResultSet rs = stmt.executeQuery(sqlIzpisIdIme);
            while(rs.next()){
                stevilo = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stevilo;
    }

    public int tujId( String imeTabele, String imeVrste, int imePolja){
        String sqlIzpisIdIme = "SELECT id FROM " + imeTabele + " WHERE " + imeVrste + " = '" + imePolja + "';";
        int stevilo = 0;
        try {
            stmt = povezava.createStatement();
            ResultSet rs = stmt.executeQuery(sqlIzpisIdIme);
            while(rs.next()) {
                stevilo = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stevilo;
    }
/*
    public int idKupca(String imekupca){
        String sqlIzpisNarocila = "SELECT id FROM Skladisca WHERE imeSkladisca = '" + imekupca + "';";
        int stevilo = 0;
        try {
            stmt = povezava.createStatement();
            stmt.executeQuery(sqlIzpisNarocila);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stevilo;
    }
*/
    public void dodajDobavnico(String datumDobavnice, String datumDostave, int stevilkaDobavnice, int idSkladisca, int idDobavitelja, int idUporabnika) {
        String sqlInsertDobavnico = "INSERT INTO Dobavnica (datumDobavnice, datumDostave, stevilkaDobavnice, idSkladisca, idDobavitelja, idUporabnika) " +
                "VALUES ('" + datumDobavnice + "', " + datumDostave + ", '" + stevilkaDobavnice + "', '" + idSkladisca + "', '" + idDobavitelja + "', '" + idUporabnika + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertDobavnico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajPrevzemnico(String datumPrevzemnice, int stevilkaPrevzemnice, int idKupca, int idSkladisca) {
        String sqlInsertDobavnico = "INSERT INTO Prevzemnica (datumPrevzemnice, stevilkaPrevzemnice, idKupca, idSkladisca) " +
                "VALUES (" + datumPrevzemnice + ", '" + stevilkaPrevzemnice + "', '" + idKupca + "', '" + idSkladisca + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertDobavnico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajIzdelkeVDobavnici(int idDobavnice, int idIzdelka){
        String sqlInsertIzdelekVDobavnico = "INSERT INTO IzdelkiVDobavnici (idDobavnice, idIzdelka) " +
                "VALUES ('" + idDobavnice + "', '" + idIzdelka + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertIzdelekVDobavnico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajIzdelkeVPrevzemnico(int idPrevzemnice, int idIzdelka){
        String sqlInsertIzdelekVDobavnico = "INSERT INTO IzdelkiVPrevzemnici (idPrevzemnice, idIzdelka) " +
                "VALUES ('" + idPrevzemnice + "', '" + idIzdelka + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertIzdelekVDobavnico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ////To imam pod dodaj količino
    public void dodajIzdelkeVSkladiscu(int idPrevzemnice, int idIzdelka){
        String sqlInsertIzdelekVDobavnico = "INSERT INTO IzdelkiVPrevzemnici (idPrevzemnice, idIzdelka) " +
                "VALUES ('" + idPrevzemnice + "', '" + idIzdelka + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertIzdelekVDobavnico);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> izpisiVseIzdelke(){
        String izpis = "SELECT i.imeIzdelka, s.imeSkladisca, ivs.Zaloga FROM Izdelki as i " +
                "JOIN IzdelkiVSkladiscu as ivs " +
                "ON i.id = ivs.idIzdelka " +
                "JOIN Skladisca as s " +
                "ON s.id = ivs.idSkladisca";
        ArrayList<String> izpisPodatkov= new ArrayList<String>();
        try {
            stmt = povezava.createStatement();
            ResultSet rs = stmt.executeQuery(izpis);
            while(rs.next()){
                izpisPodatkov.add(String.valueOf(rs.getObject("imeIzdelka")));
                izpisPodatkov.add(String.valueOf(rs.getObject("imeSkladisca")));
                izpisPodatkov.add(String.valueOf(rs.getObject("zaloga")));
                System.out.println(izpisPodatkov);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izpisPodatkov;
    }

    public ArrayList<String> izpisiIzdelek(String imeIzdelka, String imeSkladisca){
        String izpis = "SELECT i.imeIzdelka, s.imeSkladisca, ivs.Zaloga FROM Izdelki as i " +
                "JOIN IzdelkiVSkladiscu as ivs " +
                "ON i.id = ivs.idIzdelka " +
                "JOIN Skladisca as s " +
                "ON s.id = ivs.idSkladisca " +
                "WHERE imeIzdelka = '" + imeIzdelka + "' " +
                "AND imeSkladisca = '" + imeSkladisca + "'";
        ArrayList<String> izpisPodatkov= new ArrayList<String>();
        try {
            stmt = povezava.createStatement();
            ResultSet rs = stmt.executeQuery(izpis);
            while(rs.next()){
                izpisPodatkov.add(String.valueOf(rs.getObject("imeIzdelka")));
                izpisPodatkov.add(String.valueOf(rs.getObject("imeSkladisca")));
                izpisPodatkov.add(String.valueOf(rs.getObject("zaloga")));
                System.out.println(izpisPodatkov);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izpisPodatkov;
    }

    public ArrayList<String> izpisiDobavnico(int stevilkaDobavnice){
        String izpis = "SELECT d.datumDobavnice, d.stevilkaDobavnice, s.imeSkladisca, dob.imeDobavitelja, u.imeUporabnika " +
                "FROM Dobavnica as d " +
                "JOIN Skladisca as s " +
                "on s.id = d.idSkladisca " +
                "JOIN Dobavitelji as dob " +
                "on dob.id = d.idDobavitelja " +
                "JOIN Uporabniki as u " +
                "on u.id = d.idUporabnika " +
                "WHERE stevilkaDobavnice = '" + stevilkaDobavnice +"'";
        ArrayList<String> izpisPodatkov= new ArrayList<String>();
        try {
            stmt = povezava.createStatement();
            ResultSet rs = stmt.executeQuery(izpis);
            while(rs.next()){
                izpisPodatkov.add("Datum dobavnice: " + String.valueOf(rs.getObject("datumDobavnice") + "\n"));
                izpisPodatkov.add("Številka dobavnice: " + String.valueOf(rs.getObject("stevilkaDobavnice") + "\n"));
                izpisPodatkov.add("Ime skladišča: " + String.valueOf(rs.getObject("imeSkladisca") + "\n"));
                izpisPodatkov.add("Ime dobavitelja: " + String.valueOf(rs.getObject("imeDobavitelja") + "\n"));
                izpisPodatkov.add("Ime uporabnika: " + String.valueOf(rs.getObject("imeUporabnika") + "\n"));
                System.out.println(izpisPodatkov);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izpisPodatkov;
    }

    public ArrayList<String> izpisiPrevzemnico(String datumPrevzema, String datumPrevzemnice, int idDobavitelja, int idUporabnika, int idPrevzemnice){
        String izpis = "SELECT d.datumDobavnice, d.stevilkaDobavnice, s.imeSkladisca, k.imeKupca FROM Kupci as k " +
                "JOIN Dobavnica as d " +
                "on k.id = d.idKupca " +
                "JOIN Skladisca as s " +
                "on s.id = d.idSkladisca " +
                "WHERE stevilkaDobavnice = '" + idPrevzemnice +"'";
        ArrayList<String> izpisPodatkov= new ArrayList<String>();
        try {
            stmt = povezava.createStatement();
            ResultSet rs = stmt.executeQuery(izpis);
            while(rs.next()){
                izpisPodatkov.add(String.valueOf(rs.getObject("datumDobavnice")));
                izpisPodatkov.add(String.valueOf(rs.getObject("stevilkaDobavnice")));
                izpisPodatkov.add(String.valueOf(rs.getObject("imeSkladisca")));
                izpisPodatkov.add(String.valueOf(rs.getObject("imeKupca")));
                System.out.println(izpisPodatkov);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izpisPodatkov;
    }

    public ArrayList<String> izpisiIzdelekVDobavnici(String imeIzdelka, int stevilkaDobavnice){
        String izpis = "SELECT i.imeIzdelka FROM Izdelki as i " +
                "JOIN Dobavnica as d " +
                "on k.id = d.idKupca " +
                "JOIN Skladisca as s " +
                "on s.id = d.idSkladisca " +
                "WHERE idIzdelk = '" + imeIzdelka +"'";
        ArrayList<String> izpisPodatkov= new ArrayList<String>();
        try {
            stmt = povezava.createStatement();
            ResultSet rs = stmt.executeQuery(izpis);
            while(rs.next()){
                izpisPodatkov.add(String.valueOf(rs.getObject("datumDobavnice")));
                izpisPodatkov.add(String.valueOf(rs.getObject("stevilkaDobavnice")));
                izpisPodatkov.add(String.valueOf(rs.getObject("imeSkladisca")));
                izpisPodatkov.add(String.valueOf(rs.getObject("imeKupca")));
                System.out.println(izpisPodatkov);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izpisPodatkov;
    }

    public ArrayList<String> izpisiIzdelekVPrevzemnici(String datumPrevzema, String datumPrevzemnice, int idDobavitelja, int idUporabnika, int idPrevzemnice){
        String izpis = "SELECT d.datumDobavnice, d.stevilkaDobavnice, s.imeSkladisca, k.imeKupca FROM Kupci as k " +
                "JOIN Dobavnica as d " +
                "on k.id = d.idKupca " +
                "JOIN Skladisca as s " +
                "on s.id = d.idSkladisca " +
                "WHERE stevilkaDobavnice = '" + idPrevzemnice +"'";
        ArrayList<String> izpisPodatkov= new ArrayList<String>();
        try {
            stmt = povezava.createStatement();
            ResultSet rs = stmt.executeQuery(izpis);
            while(rs.next()){
                izpisPodatkov.add(String.valueOf(rs.getObject("datumDobavnice")));
                izpisPodatkov.add(String.valueOf(rs.getObject("stevilkaDobavnice")));
                izpisPodatkov.add(String.valueOf(rs.getObject("imeSkladisca")));
                izpisPodatkov.add(String.valueOf(rs.getObject("imeKupca")));
                System.out.println(izpisPodatkov);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return izpisPodatkov;
    }
    
/*
    public void izbrisi(){
        String sqlIzbrisiX = "DELETE FROM Dobavitelji "
        //ko dodajo postane true, potem pa nastavim na false
        -ko berem iz baze pogledat samo te ki so aktivni
        -
    }

 */
}