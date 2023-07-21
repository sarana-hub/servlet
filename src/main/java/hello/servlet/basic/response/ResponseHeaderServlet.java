package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //[status-line]     //HTTP 응답코드 지정
        resp.setStatus(HttpServletResponse.SC_OK); //resp.setStatus(200); //기본이 200

        //[response-headers]    //헤더 생성
        resp.setHeader("Content-Type", "text/plain;charset=utf-8"); //직접 세팅
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header","hello");

        //[message body]   //메시지 바디 생성
        PrintWriter writer = resp.getWriter();
        writer.println("안녕.");       //단순 텍스트 응답


        //[Header 편의 메서드]
        content(resp);
        cookie(resp);
        redirect(resp);
    }

    /** content 편의 메소드*/
    private void content(HttpServletResponse resp) {
        /*Content-Type: text/plain;charset=utf-8
         Content-Length: 3*/
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        //resp.setContentLength(3); //생략시 자동 생성
    }

    /** 쿠키 편의 메소드*/
    private void cookie(HttpServletResponse resp) {
        /*Set-Cookie: myCookie=good; Max-Age=600;*/
        //resp.setHeader("Set-Cookie", "myCookie=good; Max-Age=600"); //직접 세팅
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //이 쿠키는 600초동안 유효
        resp.addCookie(cookie);
    }

    /** redirect 편의 메소드*/
    private void redirect(HttpServletResponse resp) throws IOException {
        /*Status Code 302
          Location: /basic/hello-form.html*/

        /*
        resp.setStatus(HttpServletResponse.SC_FOUND); //302
        resp.setHeader("Location", "/basic/hello-form.html");
        */
        resp.sendRedirect("/basic/hello-form.html");
    }
}
