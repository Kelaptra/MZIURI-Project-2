import java.sql.*;

public class UserManager {
    private static UserManager userManager;
    private final String connURL = "jdbc:mysql://localhost:3306/project2";
    private final String connUsername = "root";
    private final String connPassword = "worldInMyEyes!";
    private Connection conn = null;
    private UserManager() throws SQLException {

        conn = DriverManager.getConnection(connURL, connUsername, connPassword);

    }

    public static UserManager getUserManager() throws SQLException {
        if(userManager == null){
            userManager = new UserManager();
        }
        return userManager;
    }

    public void addMessage(String username, String content) throws SQLException {
        System.out.println("shemovida addMessage-shi");
        PreparedStatement ps = conn.prepareStatement("insert into message (username, content) values (?, ?);");
        ps.setString(1, username);
        ps.setString(2, content);
        ps.execute();
        System.out.println("daamtavra addMessage");

    }
    public String getMessages(String username) throws SQLException {
        StringBuilder msgsAsOne = new StringBuilder();
        PreparedStatement ps = conn.prepareStatement("select content from message where username = ?");
        ps.setString(1, username);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while(rs.next()){
            msgsAsOne.append(rs.getString(1)).append("\n");
        }
        System.out.println(msgsAsOne);
        return msgsAsOne.toString();
    }
    public void addUser(String username, String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into user values (?, ?);");
        ps.setString(1, username);
        ps.setString(2, password);
        ps.execute();
    }

    public boolean searchForUsernameInTableUser(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from user where username = ?");
        ps.setString(1, username);
        ps.execute();
        ResultSet rs = ps.getResultSet();
//        System.out.println(rs.next() + ": " + rs.getString(1) + " & " + rs.getString(2));

        return rs.next();
    }
    public boolean searchForUsernameInTableMessage(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from message where username = ?");
        ps.setString(1, username);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        return rs.next();
    }

    // message -tan:

    // table-shi raw-s chamateba: (username, content) - is;
    // table-dan username-ze vigebt content-ebs;

    // user -tan:

    // table-shi raw-s chamateba: (username, password) - is;
}
