package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler); //해당 컨트롤러(handler)를 처리할 수 있는지 판단

    ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException;
    //실제 컨트롤러를 호출하고, 그 결과로 ModelView를 반환
    // 실제 컨트롤러가 ModelView를 반환하지 못하면, 어댑터가 대신 ModelView를 직접 생성해서라도 반환해야 한다.

}
