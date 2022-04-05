package com.liuxuan;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: liuxuan
 * @date: 2022-04-04 19:06
 **/

@Slf4j
@WebServlet("/logdemo")
public class Log4jDemo extends HttpServlet {
    //private static Logger logger = LoggerFactory.getLogger(Log4jTestDemo.class);
        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             //获取当前项目的根目录
                //String relativelyPath=System.getProperty("user.dir");
                // 部署到tomcat之后，这个目录变成了tomcat的bin目录
                String filePath = req.getServletContext().getRealPath("/");
                System.out.println(filePath); // 文件存放的路径
                // 指定properties文件位置
                PropertyConfigurator.configure(filePath + "WEB-INF/classes/conf/log4j.properties");
                // getOutputStream字节输出流
                ServletOutputStream out = resp.getOutputStream();
                out.write("hi".getBytes());

                // 记录debug级别的信息
                log.debug("This is debug message.");
                // 记录info级别的信息
                log.info("This is info message.");
                // 记录error级别的信息
                log.error("This is error message.");

                ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                Date date = new Date();
                out.write(String.valueOf(date).getBytes());
        }

}