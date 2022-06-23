package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyJsonServlet", urlPatterns="/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {
    //JSON 결과를 파싱해서 사용할수있는 자바객체로 변환하려면, JSON변환라이브러리(ObjectMapper)를 추가해 사용해야함
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream=req.getInputStream();
        String messageBody= StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);


        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData.username = " + helloData.getUsername());  //hello
        System.out.println("helloData.age = " + helloData.getAge());    //20

        resp.getWriter().write("ok");
    }
}
