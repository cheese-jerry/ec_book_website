package myspringmvc;

import org.thymeleaf.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{
    private Map<String,Object> beanMap = new HashMap<>(); //use map contain bean

    public DispatcherServlet(){

    }

    public void init() throws ServletException{
        super.init();
        try{
            //读取配置文件
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            //解析文件把类放入哈希表
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for(int i = 0;i<beanNodeList.getLength();i++){
                Node beanNode = beanNodeList.item(i);

                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element)beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Object beanObj = Class.forName(className).newInstance();
                    beanMap.put(beanId,beanObj);

                }
            }
            System.out.println(beanMap);
            System.out.println(beanMap.size());
            //组装层与层之间耦合关系
            for(int i = 0;i<beanNodeList.getLength();i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    NodeList beanChildNodeList = beanElement.getChildNodes();
                    for (int j = 0; j < beanChildNodeList.getLength(); j++) {
                        Node beanChildNode = beanChildNodeList.item(j);
                        if(beanChildNode.getNodeType()== Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref"); //找到需要的类
                            Object refobj = beanMap.get(propertyRef); //给类的属性赋值，refobj是值 beanobj是类
                            Object beanobj = beanMap.get(beanId);
                            Class beanclazz = beanobj.getClass();

                            Field protertyfield = beanclazz.getDeclaredField(propertyName);
                            protertyfield.setAccessible(true);
                            protertyfield.set(beanobj,refobj);
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //获得需要的类的bean 从map中拿
        String servletPath = req.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0,lastDotIndex);



        Object controllerBean = beanMap.get(servletPath); //拿到需要的类
        String operate = req.getParameter("operate"); //拿到请求作用域中的value


        if(StringUtils.isEmpty(operate)){
            operate = "index";
        }


        Method[] methods = controllerBean.getClass().getDeclaredMethods();


        for(Method m:methods){ //找要的方法
            String methodName = m.getName();
            if(operate.equals(methodName)){
                try{
                    //1得到参数
                    Parameter[] parameters = m.getParameters();
                    System.out.println(m);

                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parametername = parameter.getName();
                        if(parametername.equals("req")){
                            parameterValues[i] = req;
                        }else if(parametername.equals("resp")){
                            parameterValues[i] = resp;
                        }else if(parametername.equals("session")){
                            parameterValues[i] = req.getSession();
                        }else {

                            String parameterValue = req.getParameter(parametername); //从作用域得到值
                            String typename = parameter.getType().getName();

                            System.out.println(parameterValue);
                            Object para = null;
                            if("java.lang.Integer".equals(typename)){
                                para = Integer.parseInt(parameterValue); //后可加double 等
                            }else if("java.lang.Double".equals(typename)){
                                para = Double.parseDouble(parameterValue); //后可加double 等
                            }else if (typename.equals("class java.time.LocalDateTime")) {
                                para = Timestamp.valueOf((LocalDateTime) para);
                            } else{
                                para = parameterValue;
                            }
                            parameterValues[i] = para;

                        }
                    }
                    //2用反射调用方法
                    m.setAccessible(true);

                    Object returnvalue = m.invoke(controllerBean,parameterValues);
                    String methodreturnstr = (String) returnvalue;

                    //3重定向或跳转页面
                    if(methodreturnstr.startsWith("redirect:")){
                        String redirecstr = methodreturnstr.substring("redirect:".length());
                        resp.sendRedirect(redirecstr);
                    }else{
                        super.processTemplate(methodreturnstr,req,resp);
                    }

                    return;
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }catch (InvocationTargetException e){
                    e.printStackTrace();
                }
            }
        }


    }
}
