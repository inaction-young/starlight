package com.starlight.core.config;

import com.google.common.collect.Lists;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

	public RestTemplate restTemplate(HttpClient httpClient) {
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
	}

	@Bean
	public HttpClient httpClient() {
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", SSLConnectionSocketFactory.getSocketFactory())
				.build();

		//连接池配置
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		// 最大连接数
		connectionManager.setMaxTotal(1000);
		// 单个路由最大连接数
		connectionManager.setDefaultMaxPerRoute(500);
		// 空闲连接空闲时间超过指定时间，连接租借给用户前，尝试读一次服务端(超时1ms)来判断有没有失效, 默认2s
		connectionManager.setValidateAfterInactivity(500);

		//请求配置
		RequestConfig requestConfig = RequestConfig.custom()
				//连接(握手成功)等待时长，超出抛出connect timeout
				.setConnectTimeout(2000)
				//响应(response)等待时长，超过抛出read timeout
				.setSocketTimeout(3000)
				//从连接池中获取连接等待时长，超时间未拿到可用连接，会抛出ConnectionPoolTimeoutException: Timeout waiting for connection from pool
				.setConnectionRequestTimeout(300)
				.build();

		//headers
		List<Header> headers = Lists.newArrayList();
		//长链接
		headers.add(new BasicHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE));
		//json
		headers.add(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));

		return HttpClientBuilder.create()
				.setDefaultRequestConfig(requestConfig)
				.setConnectionManager(connectionManager)
				.setDefaultHeaders(headers)
				// 连接池不是共享模式，这个共享是指与其它httpClient是否共享
				.setConnectionManagerShared(false)
				//设置连接池中连接存活时间，如果不设置或设置-1，则由response header "Keep-Alive: timeout"决定，读不到Keep-Alive则永久存活。
				.setConnectionTimeToLive(30, TimeUnit.SECONDS)
				//用来关闭闲置连接，它会启动一个守护线程进行清理工作。用户可以通过builder#evictIdleConnections开启该组件，并通过builder#setmaxIdleTime设置最大空闲时间。
				.evictIdleConnections(10, TimeUnit.SECONDS)
				// 回收过期连接
				.evictExpiredConnections()
				// 保持长连接配置，需要在头添加Keep-Alive
				.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
				//重试次数，默认是3次，没有开启
//				.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true))
				.build();
	}
}
