package com.alexandria.books.jwtauth;

import com.alexandria.books.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class JwtAuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtTokenUtil;

  @Autowired
  private MyUserDetailsService userDetailsService;

  @RequestMapping(
    value = "/authenticate",
    method = RequestMethod.POST,
    consumes = "application/json"
  )
  public ResponseEntity<?> createAuthenticationToken(
    @RequestBody JwtAuthRequest authenticationRequest
  ) throws Exception {

    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          authenticationRequest.getUsername(), authenticationRequest.getPassword()
        )
      );
    }
    catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new JwtAuthResponse(jwt));
  }

}
