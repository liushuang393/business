package org.com.business.spring.boot.fw.core.auth.config;

import java.util.List;

import org.com.business.spring.boot.fw.core.auth.service.CostmUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// AbstractUserDetailsAuthenticationProviderを継承することで、retrieveUserのみを実装すればOK
public class CostmUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final String DUMMY_PASSWORD = "DUMMY_PASSWORD"; // ※認証では使用しないため、値は何でもよい(nullや空文字列はNG)
    private static final List<GrantedAuthority> AUTH_USER = AuthorityUtils.createAuthorityList("USER"); // ※本サンプルでは、全員この権限とする

    // <<< N/A >>>
    //    @Override
    //    protected void additionalAuthenticationChecks(UserDetails userDetails,
    //            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    //    }
    //---

    @Autowired
    CostmUserDetailsService userDetailsService;

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        String userId = username;
        String password = (String) authentication.getCredentials();
        // ユーザIDとパスワードをチェック
        boolean isValid = true;

        UserDetails user = userDetailsService.loadUserByUsername(userId);

        //TOdO    AuthApi.isValidUserIdAndPassword(userId, password); // ※外部ライブラリのAPIで認証する擬似コード
        if (!isValid) {
            throw new UsernameNotFoundException(username);
        }

        // UserDetailsの実装(User)を生成し戻り値とする
        return user;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }
}