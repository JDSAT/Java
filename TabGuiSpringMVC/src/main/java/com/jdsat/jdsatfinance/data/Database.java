package com.jdsat.jdsatfinance.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jdsat.jdsatfinance.model.Person;

public class Database {
    static final String CREATE_PERSON_TABLE =
        "CREATE table PERSONTABLE ("+
            "ID          INTEGER NOT NULL "+
            "PRIMARY KEY GENERATED ALWAYS AS IDENTITY "+
            "(START WITH 1, INCREMENT BY 1)," +
            "NAME           VARCHAR(50)";

    String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    //"org.apache.derby.jdbc.ClientDriver";  <- to connect to standalone JavaDB
    String url = "jdbc:derby:jdsatdb";
    String usr = "dbuser";
    String pwd = "dbuser";
    static Connection con = null;
    final Object lock = new Object();

    // Singleton
    protected static final Database _INSTANCE = new Database();
    public static Database getInstance() { return _INSTANCE; }

    protected Database() {
        try {
            if ( con == null ) {
                System.setProperty("derby.database.forceDatabaseLock", "true");
                Class.forName( driver ).newInstance();
                con = DriverManager.getConnection( url, usr, pwd );
            }
        }
        catch ( Exception e ) {
            // Database connect failed because it probably doesn't exist
            try {
                // Create the database, tables, and indexes now
                url += ";create=true";
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection( url, usr, pwd );
                System.out.println("Database created");
                createTables();
                createIndexes();
            }
            catch ( Exception e1 ) {
                e.printStackTrace();
                System.exit(0);
            }
        }

        if ( con != null )
            System.out.println("Database connected");
        else
            System.out.println("Database NOT connected");
    }

    private void createTables() {
        execQuery(CREATE_PERSON_TABLE);
        System.out.println("Database tables created");
    }

    private void createIndexes() throws Exception {
        System.out.println("Database indexes created");
    }

    private boolean execQuery(String queryStr) {
        QueryTool query = null;
        try {
            query = new QueryTool(con);
            query.setQuery( queryStr );
            query.exec();
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return false;
        }
        finally {
            query.close();
        }
        return true;
    }
    
    private Person extractPerson(ResultSet rs) throws SQLException{
        Person person=new Person();
        person.setId(rs.getInt("ID"));
        person.setName(rs.getString("NAME"));
        return person;
    }

    ////////////////////////////////////////////////////////////////////////
    
    public boolean isConnected() {
        if ( con != null )
            return true;
        return false;
    }

    public void disconnect() {
        try {
            // Close the active connection
            if ( isConnected() ) {
                con.commit();
                con.close();
            }

            // Shutdown Derby - the exception contains the shutdown message
            try {
                con = DriverManager.getConnection( "jdbc:derby:;shutdown=true", usr, pwd );
            }
            catch ( SQLException sqle ) {
                System.out.println(sqle.getMessage());
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public boolean addPerson(Person person) {
        QueryTool query = null;
        try {
            // Form and execute the query to add the book
            query = new QueryTool(con);
            String req = "INSERT INTO PERSONTABLE " +
                    "(NAME) " +
                    "VALUES(?)";
            PreparedStatement ps = query.setQuery( req );
            ps.setInt(1,person.getId());
            query.exec();
            return true;
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            query.close();
        }
        return false;
    }

//    public Book[] getBooks() {
//        QueryTool query = null;
//        try {
//            query = new QueryTool(con);
//            String req = "SELECT * FROM APP.BOOK ORDER BY NAME"; // sorted
//            PreparedStatement ps = query.setQuery( req );
//            ResultSet rs = query.exec();
//
//            // Iterate through the resultset, gather the data for each
//            // book returned, and create a corresponding Book object
//            Vector bookVect = new Vector();
//            while ( rs.next() ) {
//                Book book = extractBookData(rs);
//                if ( book.active > 0 )
//                    bookVect.add(book);
//            }
//
//            // Convert the Vector to an array
//            Book[] books =  new Book[bookVect.size()];
//            bookVect.toArray(books);
//            return books;
//        }
//        catch ( Exception e ) {
//            e.printStackTrace();
//        }
//        finally {
//            query.close();
//        }
//        return null;
//    }

    public Person getPerson(int id) {
        QueryTool query = null;
        try {
            query = new QueryTool(con);
            String req = "SELECT * FROM PERSONTABLE WHERE \"ID\"=" + id;
            PreparedStatement ps = query.setQuery(req);
            ResultSet rs = query.exec();
            if ( rs.next() )
                return extractPerson(rs);
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            query.close();
        }

        return null;
    }
}
