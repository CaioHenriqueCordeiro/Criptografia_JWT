package com.caiohenrique.projetologin.service;

import com.caiohenrique.projetologin.data.DetalheUsuarioData;
import com.caiohenrique.projetologin.entities.User;
import com.caiohenrique.projetologin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(username);

        if(!user.isPresent())
            throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");

        return new DetalheUsuarioData(user);
    }
}
