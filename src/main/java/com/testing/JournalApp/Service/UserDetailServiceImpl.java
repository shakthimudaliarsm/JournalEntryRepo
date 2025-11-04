package com.testing.JournalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.testing.JournalApp.Entity.User;
import com.testing.JournalApp.Repository.UserRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		if(user!=null) {
			System.out.println("Username "+user.getUsername()+""+user.getRoles()+""+user.getPassword());
			UserDetails u = org.springframework.security.core.userdetails.User.builder()
				    .username(user.getUsername())
				    .password(user.getPassword())
				    .roles(user.getRoles().toArray(new String[0]))
				    .build();

				System.out.println("Authorities: " + u.getAuthorities());
				System.out.println(
					    new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()
					        .matches("test1234#", "$2a$10$HntD.zFqxaHcdzB2zQxqKOr4NdyJCG9opUgoHPaRX0.5cFDn1Caia")
					);
				return u;
			
		}
		
		
		throw new UsernameNotFoundException("User Name not found");
		
	}

}
