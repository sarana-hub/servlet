package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**프론트 컨트롤러*/

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
//front-controller/v1 를 포함한 하위 모든 요청은, 이 서블릿에서 받아들인다

public class FrontControllerServletV1 extends HttpServlet {
    private Map<String,ControllerV1> controllerMap=new HashMap<>();
    //controllerMap(key: 매핑 URL, value: 호출될 컨트롤러)

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = req.getRequestURI(); //requestURI 를 조회해서

        // 실제 호출할 컨트롤러를 controllerMap 에서 찾는다
        ControllerV1 controller = controllerMap.get(requestURI);
        // = "ControllerV1 controller =  new MemberListControllerV1();" //다형성

        if (controller == null) { //만약 없다면 404(SC_NOT_FOUND) 상태 코드를 반환한다
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(req, resp); //해당 컨트롤러(찾은 컨트롤러)를 실행
    }
}
