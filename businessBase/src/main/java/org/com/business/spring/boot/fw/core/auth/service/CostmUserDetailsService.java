package org.com.business.spring.boot.fw.core.auth.service;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.com.business.spring.boot.fw.core.auth.model.CustomUserDetails;
import org.com.business.spring.boot.fw.persistence.entity.User;
import org.com.business.spring.boot.fw.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CostmUserDetailsService implements UserDetailsService {

	@Resource
	UserMapper userMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		log.info("ログインユーザ: {}", username);

		// 認証を行うユーザー情報を格納する
		User dbUser = null;

		try {
			// 入力したユーザーIDから認証を行うユーザー情報を取得する
			dbUser = userMapper.selectByPrimaryKey(username);
		} catch (Exception e) {
			// 取得時にExceptionが発生した場合
			throw new UsernameNotFoundException("It can not be acquired User");
		}

		// ユーザー情報を取得できなかった場合
		if (dbUser == null) {
			throw new UsernameNotFoundException(
					"User not found for login id: " + username);
		}
		// ①
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		// for (Role role : user.getRoles()) {
		// grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		// }

		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		CustomUserDetails user = new CustomUserDetails(username,
				passwordEncoder.encode(dbUser.getPassword()),
				grantedAuthorities);

		// ユーザー情報が取得できたらSpring Securityで認証できる形で戻す

		// ②
		// CustomUserDetails user = new CustomUserDetails(username,
		// passwordEncoder.encode(dbUser.getPassword()),
		// AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));

		//
		// CustomUserDetails user = new CustomUserDetails(username,
		// passwordEncoder.encode(dbUser.getPassword()),
		// AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));

		user.setName(dbUser.getUserName());

		return user;
	}

	// public String findLoggedInUsername() {
	// Object userDetails =
	// SecurityContextHolder.getContext().getAuthentication().getDetails();
	// if (userDetails instanceof UserDetails) {
	// return ((UserDetails) userDetails).getUsername();
	// }
	//
	// return null;
	// }

	// public void autoLogin(String username, String password) {
	// UserDetails userDetails =
	// userDetailsService.loadUserByUsername(username);
	// UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	// new UsernamePasswordAuthenticationToken(
	// userDetails, password, userDetails.getAuthorities());
	//
	// authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	//
	// if (usernamePasswordAuthenticationToken.isAuthenticated()) {
	// SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	// if (log.isDebugEnabled()) {
	// log.debug(String.format("Auto login %s successfully!", username));
	// }
	//
	// }
	// }
}