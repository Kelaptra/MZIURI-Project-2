import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String content = req.getParameter("message");
        System.out.println("messageServlet: " + username + " " + content);
        try{
            if(Validator.getValidator().checkForNoSuchUsernameToSendAMessage(username) || Validator.getValidator().checkForNewLine(username)){
                resp.setStatus(403);
                return;
            }
            UserManager.getUserManager().addMessage(username, content);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        resp.setStatus(200);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String msgs;
        try{
            if(Validator.getValidator().checkForNoSuchUsernameToGetMessages(username)){
                resp.setStatus(403);
                return;
            }
            msgs = UserManager.getUserManager().getMessages(username);
            resp.setStatus(200);
            resp.getWriter().write("<h1>\n" + msgs + "</h1>");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // post:
    // 1.   2 par: username, message;
    // 2.   database-shi (tableshi) shesabamis username-ze vinaxavt message-s
    // 3.   response: carieli/200/...

    // get:
    // 1.   2 par: username, password;
    // 2.   database-shi (table-dan) username-ze vigebt message-ebs
    // 3.   tu gamova:
    //          pirdapir string[] shi shevinaxot;
    //      tu arada:
    //          message-ebi ert stringshi shevinaxot
    //          message-ebi gamovyot "\n"-ebit
    // 4.   response-shi gadavcemt message-ebian string-s/array-s
}
