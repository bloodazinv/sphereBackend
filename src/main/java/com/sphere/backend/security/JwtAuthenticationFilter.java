/**
 * FileName: JwtAuthenticationFilter
 * Author: jane
 * Date: 2023/4/9 19:46
 * Description:
 * Version:
 */

package com.sphere.backend.security;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Value("${app.jwtTokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader(tokenHead);
        String jwt = getJwtFromRequest(request);
        //判断当前请求中包含令牌
        if (!StringUtils.isEmpty(token)) {
            //token中获取用户的角色权限信息
            Claims claims = tokenProvider.getTokenClaim(token);
            if (claims != null) {
                //如果token未失效 并且 当前上下文权限凭证为null
                if (!tokenProvider.isTokenExpired(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    /**
                     * 这里省略了查询数据库token存不存在,有没有失效这个步骤
                     * 如果是做单点登录,后登录的人登录时候把上一个人的token状态改为失效,这样就能保证同一时间一个帐号只能有一个人能使用
                     */

                    //把用户权限信息放到上下文中
                    Long userId = tokenProvider.getUserIdFromJWT(jwt);
                    UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
