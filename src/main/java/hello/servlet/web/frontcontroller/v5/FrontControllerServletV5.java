package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;

import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>(); //매핑 정보 Object
    private final List<MyHandlerAdapter> handlerAdapters=new ArrayList<>();

    public FrontControllerServletV5(){
        initHandlerMappingMap();    //핸들러 매핑을 초기화(등록)
        initHandlerAdapters();      //어댑터를 초기화(등록)
    }

    private void initHandlerMappingMap() {  //핸들러 매핑정보( handlerMappingMap )에 컨트롤러를 추가
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }
    private void initHandlerAdapters() {    // 해당 컨트롤러를 처리할 수 있는 어댑터도 추가
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Object handler=getHandler(req); /**1. 핸들러 찾기*/
        if (handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter=getHandlerAdapter(handler);  /**2. 핸들러를 처리할 수 있는 핸들러어댑터 찾기*/

        ModelView mv = adapter.handle(req, resp, handler);    /**3. 실제 어댑터 호출*/
        //4. 어댑터는 handler(컨트롤러)를 호출하고, 그 결과를 어댑터에 맞추어(ModelView) 반환

        MyView view= viewResolver(mv.getViewName());
        view.render(mv.getModel(), req, resp);
    }


    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return handlerMappingMap.get(requestURI);
        //핸들러 매핑정보(handlerMappingMap)에서, URL에 매핑된 핸들러(컨트롤러) 객체를 찾아서 반환
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {  //핸들러를 처리할 수 있는 어댑터가 있는지
                return adapter;
                //핸들러가 ControllerV3인터페이스를 구현했다면 (supports()가 true이면),
                // ControllerV3HandlerAdapter 객체가 반환된다
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없어요! handler=" + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
