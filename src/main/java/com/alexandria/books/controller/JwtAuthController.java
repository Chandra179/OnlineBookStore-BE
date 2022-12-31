package com.alexandria.books.controller;

import com.alexandria.books.config.MyUserDetailsService;
import com.alexandria.books.dto.jwtauh.JwtAuthRequest;
import com.alexandria.books.dto.jwtauh.JwtAuthResponse;
import com.alexandria.books.jwtauth.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Authentication")
@RestController
class JwtAuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtTokenUtil;

  @Autowired
  private MyUserDetailsService userDetailsService;

  @Operation(
    description = "Authenticate user",
    tags = {"Authentication"}
  )
  @ApiResponses(
    value = {
      @ApiResponse(
        responseCode = "200",
        description = "Success authenticate user",
        content = @Content(schema = @Schema(implementation = JwtAuthResponse.class))
      ),
      @ApiResponse(
        responseCode = "403",
        description = "Failed authenticate user",
        content = @Content(schema = @Schema(implementation = ResponseStatusException.class))
      )
    }
  )
  @PostMapping(
    value = "/authenticate",
    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE}
  )
  public JwtAuthResponse authenticateUser(
    @RequestBody JwtAuthRequest authenticationRequest
  ) throws ResponseStatusException {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          authenticationRequest.getUsername(), authenticationRequest.getPassword()
        )
      );
    }
    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect username or password");
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return new JwtAuthResponse(jwt);
  }

}
