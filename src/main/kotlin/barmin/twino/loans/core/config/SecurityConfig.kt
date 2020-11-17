package barmin.twino.loans.core.config

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests().antMatchers("/user/**").permitAll()
            .and()
            .authorizeRequests()
            .antMatchers("/loans/**").authenticated()
            .and()
            .formLogin()
            .and()
            .logout().logoutSuccessUrl("/")
            .and().csrf().disable()

    }
    @Bean
    fun users() = InMemoryUserDetailsManager()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}

@RestController
@RequestMapping("/user")
class UserController(private val userManager: UserDetailsManager, private val passwordEncoder: PasswordEncoder) {

    @PostMapping
    fun create(@RequestBody user: UserRequest) {
        userManager.createUser(
            User.builder()
                .username(user.username)
                .password(user.password)
                .authorities(user.authorities.map { SimpleGrantedAuthority(it) })
                .passwordEncoder { passwordEncoder.encode(it) }
                .build()
        )
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    fun getUser(authentication: Authentication) = authentication.principal
}

class UserRequest {
    var username: String? = null
    var password: String? = null
    var authorities: Set<String> = emptySet()
}

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun processActionHandlingException(ex: Exception, request: WebRequest) =
        handleExceptionInternal(
            ex,
            mapOf(
                "success" to false,
                "message" to ex.message,
                "class" to ex.javaClass.name // todo only for debug
            ),
            HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request
        )
}