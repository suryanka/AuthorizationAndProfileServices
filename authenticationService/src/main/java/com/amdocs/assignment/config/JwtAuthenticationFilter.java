package com.amdocs.assignment.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.amdocs.assignment.helper.JwtUtil;
import com.amdocs.assignment.service.CustomUserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
	
	private String globalUsername="";
	
	
	public String getGlobalUsername() {
		return globalUsername;
	}


	public void setGlobalUsername(String globalUsername) {
		this.globalUsername = globalUsername;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				username = jwtUtil.extractUsername(jwtToken);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(username);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
//						new UsernamePasswordAuthenticationToken( userDetails,null, userDetails.getAuthorities());

				Boolean validateToken = jwtUtil.validateToken(jwtToken, userDetails);
				if (validateToken) {
					
					//setting global username
					globalUsername = username; 

					// set the authentication
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);

				} else {
					
					System.out.println("Validation fails !!");
				}

			} 

		}

		filterChain.doFilter(request, response);

	}

}
