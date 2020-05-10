package com.shivantha;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DispatcherServlet", urlPatterns = "/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Method method = MethodLoader.getMethod(request.getServletPath());
            Map<String, Object> model = new HashMap<String, Object>();
            Object o = Class.forName(method.getDeclaringClass().getName()).newInstance();

            String template = (String) method.invoke(o,model);

            ServletContext context = getServletContext();
            String fullPath = context.getRealPath("/WEB-INF/" + template + ".ftl");
            String templateContent = FileUtils.readFileToString(new File(fullPath));
            Configuration cfg = new Configuration();
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            Template t = new Template("templateName", new StringReader(templateContent), cfg);
            Writer out = new StringWriter();
            try {
                t.process(model, out);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
            String transformedTemplate = out.toString();
            response.getWriter().print(transformedTemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

