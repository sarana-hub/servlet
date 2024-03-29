package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 1. 파라미터 전송
 * http://localhost:8080/request-param?username=hello&age=20
 *
 * 2. 동일한 파라미터 전송
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */
@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---전체 파라미터 조회-start---");

        /*Enumeration<String> parameterNames = request.getParameterNames();  //모든 요청 파라미터 이름들 조회 가능
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            System.out.println(paramName + "=" + request.getParameter(paramName));
        }*/
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + req.getParameter(paramName)));

        System.out.println("---전체 파라미터 조회-end ---");
        System.out.println();


        System.out.println("---단일 파라미터 조회---");
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        System.out.println("username = " + username);    //hello
        System.out.println("age = " + age);     //20
        System.out.println();

        System.out.println("---이름이 같은 복수 파라미터 조회---");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username=" + name);  //hello  //kim
        }

        resp.getWriter().write("ok"); //웹브라우저에 출력
    }
}
