package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4); //handler가 ControllerV4인 경우에만 처리하는 어댑터
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;   //1. handler를 ControllerV4로 캐스팅

        //2. paramMap과 model을 만들어서 해당 컨트롤러를 호출
        Map<String, String> paramMap = createParamMap(req);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);  //3. viewName을 반환 받는다

        /**뷰의 이름을 ModelView로 만들어서 형식을 맞추어 반환한다.*/
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }

}
