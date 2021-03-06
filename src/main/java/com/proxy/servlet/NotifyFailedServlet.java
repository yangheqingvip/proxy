package com.proxy.servlet;

import com.proxy.model.ProxyBean;
import com.proxy.serivce.ProxyPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 通知失败
 */
public class NotifyFailedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static Logger LOG = LogManager.getLogger(NotifyFailedServlet.class);

    public NotifyFailedServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip = request.getParameter("ip");
        int port = Integer.parseInt(request.getParameter("port"));
        String errorMessage = request.getParameter("msg");
        LOG.debug("NotifyFailed [ip:{} ,port:{} ,errorMessage:{}]", ip, port, errorMessage);
        ProxyPool.getInstance().noticeFail(new ProxyBean(ip, port), errorMessage);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
