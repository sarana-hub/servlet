package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); //ControllerV3만을 처리할 수 있는 어댑터
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler; //handler를 ControllerV3로 캐스팅

        Map<String, String> paramMap = createParamMap(req); //V3 형식에 맞도록 호출
        ModelView mv = controller.process(paramMap);
        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String > paramMap=new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }

}
