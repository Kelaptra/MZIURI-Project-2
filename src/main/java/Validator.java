import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public class Validator {
    // TODO: checkings for
    //      \n
    //      username doesn't exist
    //      username already taken
    private static Validator validator;

    private Validator(){}

    public static Validator getValidator() {
        if(validator == null){
            validator = new Validator();
        }
        return validator;
    }

    public boolean checkForNewLine(String content){
        return content.contains("\n");
    }
    public boolean checkForNoSuchUsernameToSendAMessage(String username) throws SQLException {
        return !UserManager.getUserManager().searchForUsernameInTableUser(username);
    }
    public boolean checkForNoSuchUsernameToGetMessages(String username) throws SQLException {
        return !UserManager.getUserManager().searchForUsernameInTableMessage(username);
    }
    public boolean checkIfUsernameAlreadyTaken(String username) throws SQLException {
        return UserManager.getUserManager().searchForUsernameInTableUser(username);
    }

}
