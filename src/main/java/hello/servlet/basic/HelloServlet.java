package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + req);
        System.out.println("response = " + resp);

        String username = req.getParameter("username");
        System.out.println("username = " + username);

        //헤더정보
        resp.setContentType("text/plain");  //단순 문자
        resp.setCharacterEncoding("utf-8");  //인코딩 정보: 거의 다 utf-8을 사용

        resp.getWriter().write("hello "+username);
    }
}
