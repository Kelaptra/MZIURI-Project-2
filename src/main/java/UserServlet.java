import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try{
            if(Validator.getValidator().checkIfUsernameAlreadyTaken(username)){
                resp.setStatus(403);
                return;
            }
            UserManager.getUserManager().addUser(username, password);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        resp.setStatus(200);
    }
    // post:
    // 1.   2 par: username, password;
    // 2.   database-shi (table-shi) vinaxavt username-s da password-s
    // 3.   response: carieli/200/...
}
