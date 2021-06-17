package com.dev.portal.mvc.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dev.portal.models.contants.UserRoles;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").hasAnyRole(new String[] { UserRoles.ADMIN, UserRoles.ORG_ADMIN, UserRoles.CATALOG_ADMIN, UserRoles.DEVELOPER })
            .antMatchers("/admin/**").hasAnyRole(new String[] { UserRoles.ADMIN })
            .antMatchers("/org_admin/**").hasAnyRole(new String[] { UserRoles.ADMIN, UserRoles.ORG_ADMIN })
            .antMatchers("/catalog_admin/**").hasAnyRole(new String[] { UserRoles.ADMIN, UserRoles.ORG_ADMIN, UserRoles.CATALOG_ADMIN })
            .antMatchers("/developer/**").hasAnyRole(new String[] { UserRoles.ADMIN, UserRoles.ORG_ADMIN, UserRoles.CATALOG_ADMIN, UserRoles.DEVELOPER })
            .and().formLogin().permitAll()
            //.and().formLogin().loginPage("/login.html").permitAll()
            //.and().exceptionHandling().accessDeniedPage("/403.html")
            .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
        http.httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/");
        http.authorizeRequests().anyRequest().authenticated();
        http.csrf().disable();
    }

}
