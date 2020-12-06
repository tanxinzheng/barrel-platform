package com.github.tanxinzheng.cloud.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tanxinzheng.framework.model.BaseResultCode;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.framework.secure.config.SecureProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


@Slf4j
@Component
public class AuthTokenFilter implements GlobalFilter, Ordered {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private String[] skipAuthUrls;

    private ObjectMapper objectMapper;

    public AuthTokenFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Resource
    SecureProperties secureProperties;



    @Resource
    RedisTemplate redisTemplate;

    @Autowired
    public void init(){
        skipAuthUrls = secureProperties.getIgnoreUrls();
    }

    /**
     * 过滤器
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();

        //跳过不需要验证的路径
        if (ArrayUtils.isNotEmpty(skipAuthUrls)) {
            for (String skipAuthUrl : skipAuthUrls) {
                if(antPathMatcher.match(skipAuthUrl, url)){
                    return chain.filter(exchange);
                }
            }
        }
        //获取token
        String token = exchange.getRequest().getHeaders().getFirst(secureProperties.getTokenHeaderName());
        ServerHttpResponse resp = exchange.getResponse();
        if (StringUtils.isBlank(token)) {
            //没有token
            return authError(resp, "未授权登录");
        } else {
            //有token
            try {
                Object currentLoginUser = redisTemplate.opsForValue().get(secureProperties.getTokenHeaderName() + ":" + token);
                if(currentLoginUser == null){
                    return authError(resp, "无效的令牌");
                }
                return chain.filter(exchange);
            }catch (Exception e){
                log.error(e.getMessage(), e);
                return authError(resp, e.getMessage());
            }
        }
    }

    /**
     * 认证错误输出
     *
     * @param resp 响应对象
     * @param mess 错误信息
     * @return
     */
    private Mono<Void> authError(ServerHttpResponse resp, String mess) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        Result<String> returnData = Result.failed(BaseResultCode.UNAUTHORIZED, mess);
        String returnStr = "";
        try {
            returnStr = objectMapper.writeValueAsString(returnData);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
