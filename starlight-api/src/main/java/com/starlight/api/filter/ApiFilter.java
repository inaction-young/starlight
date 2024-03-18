package com.starlight.api.filter;

import com.starlight.core.utils.IpUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName: ApiFilter
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/6 下午5:45
 * @Version V1.0
 */
@Log4j2
@WebFilter(filterName = "apiFilter",urlPatterns = "/*")
@Order(Integer.MIN_VALUE)
public class ApiFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String ip = IpUtils.getIp(req);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
