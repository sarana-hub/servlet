package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//뷰로 이동하는 부분(중복)을 분리

public class MyView {   //별도로 뷰를 처리하는 객체
    private String viewPath;

    public MyView(String viewPath){
        this.viewPath =viewPath;
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
    }


    public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        modelToRequestAttribute(model, req);
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp); //JSP로 포워드 해서 JSP를 렌더링
    }
    //뷰 객체의 render() 는 모델 정보도 함께 받는다
    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req) {
        model.forEach((key, value)-> req.setAttribute(key,value));
        //JSP는 req.getAttribute() 로 데이터를 조회하기 때문에, 모델의 데이터를 모두 꺼내서 req.setAttribute() 로 담아둔다
    }
}
