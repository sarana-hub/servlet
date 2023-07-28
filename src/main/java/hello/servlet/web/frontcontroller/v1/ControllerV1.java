package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서블릿 모양의 컨트롤러 인터페이스
// 각 컨트롤러들은 이 인터페이스를 구현
public interface ControllerV1 {

    void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
