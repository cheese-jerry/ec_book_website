package myspringmvc;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@WebFilter(urlPatterns = {"*.do","*.html"},
           initParams = {
        @WebInitParam(name = "white",value = "/Book_webside_war_exploded/page.do?operate=page&page=user/login" +
                ",/Book_webside_war_exploded/user.do?null" +
                ",/Book_webside_war_exploded/book.do?null" +
                ",/Book_webside_war_exploded/page.do?operate=page&page=user/regist")
           })

public class SessionFilter implements Filter {
    List<String> whiteList = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String whi = filterConfig.getInitParameter("white");
        String[] whiteArr = whi.split(",");
        whiteList = Arrays.asList(whiteArr);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String str = uri+"?"+queryString;
        System.out.println(str);
        if(whiteList.contains(str)){
            filterChain.doFilter(request,response);
            return;
        }else{
            HttpSession session = request.getSession();
            Object currUserObj = session.getAttribute("currUser");
            if(currUserObj == null){
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else{
                filterChain.doFilter(request,response);
            }

        }

    }
}
