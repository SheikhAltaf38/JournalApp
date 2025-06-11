package com.sheikh.integratedb.service;

import com.sheikh.integratedb.models.User;
import com.sheikh.integratedb.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

public class CustomUserDetailsServiceMockitoTest {
   @InjectMocks
    CustomUserDetailsService customUserDetailsService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUserNameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("altaf").password("altaf123").roles(new ArrayList<>()).build());
        Assertions.assertNotNull(customUserDetailsService.loadUserByUsername("abjsc"));

    }
}
