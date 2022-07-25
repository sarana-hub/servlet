package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;
//서블릿의 종속성을 제거하기 위해 (HttpServletRequest 사용X)
// Model을 직접 만들고, 추가로 View 이름까지 전달하는 객체를 만듦

public class ModelView {
    private String viewName;  //뷰의 논리 이름
    private Map<String, Object> model=new HashMap<>();  //뷰를 렌더링할 때 필요한 model 객체 (뷰에 전달할 Model 데이터)

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {

        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

}
