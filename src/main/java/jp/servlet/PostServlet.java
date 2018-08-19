package jp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class PostServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=Shift_JIS");
        PrintWriter out = resp.getWriter();

        out.println(createHTML("GET"));

        out.close();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("post called.");
        req.setCharacterEncoding("Shift_JIS");
        String address = req.getParameter("address");
        System.out.println(address);
        byte[] byte_address = address.getBytes(Charset.forName("Shift_JIS"));
        StringBuilder sb = new StringBuilder();
        for (byte d: byte_address) {
            sb.append(String.format("%02X", d));
        }
        System.out.println(sb.toString());

        byte[] byte_address2 = address.getBytes(Charset.forName("UTF-8"));
        StringBuilder sb2 = new StringBuilder();
        for (byte d: byte_address2) {
            sb2.append(String.format("%02X", d));
        }
        System.out.println(sb2.toString());

        byte[] byte_address3 = address.getBytes();
        StringBuilder sb3 = new StringBuilder();
        for (byte d: byte_address3) {
            sb3.append(String.format("%02X", d));
        }
        System.out.println(sb3.toString());

        doGet(req, resp);
    }

    private String createHTML(String methodType){
        StringBuffer sb = new StringBuffer();

        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>post page</title>");
        sb.append("</head>");
        sb.append("<body>");

        sb.append("<form action=\"regist\" method=\"post\">");
        sb.append("<input type=\"text\" name=\"address\" />");
        sb.append("<input type=\"submit\" value=\"POST\">");
        sb.append("</form>");
        sb.append("</body>");
        sb.append("</html>");

        return (new String(sb));
    }
}
