import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class indexServlet extends HttpServlet{
    private String content="";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute("name")!=null){
            String name = (String) req.getAttribute("name");
            System.out.println(name);
            req.getSession().setAttribute("name",name);
            resp.sendRedirect("index.jsp");
    }

        String getName = (String) req.getSession().getAttribute("name");
        System.out.println(content);
        if(req.getParameter("content")!=null) {
            content = getName+":"+req.getParameter("content") + "***" + getName;
            System.out.println("content is " + content);
        }
        else{
            System.out.println("---------------");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getName = (String) req.getSession().getAttribute("name");
        String temp = content;
        System.out.println(getName);
        resp.setContentType("text/html;charset=utf-8");
        if (content!="" && !content.contains("***"+getName)){
            JSONObject talk = new JSONObject();
            int index = temp.indexOf("***");
            temp=temp.substring(0,index);
            talk.put("talk",temp);
            content+="***"+getName;
            System.out.println(talk);
            resp.getWriter().write(talk.toString());
        }else{
            JSONObject wrong = new JSONObject();
            wrong.put("wrong","wrong");
            wrong.put("coner",getName);
            resp.getWriter().write(wrong.toString());
        }
    }
}
