package com.assignment3.assignment3.user;

import com.assignment3.assignment3.security.dto.UpdateUserRequest;
import com.assignment3.assignment3.user.dto.UserComplexDto;
import com.assignment3.assignment3.user.dto.UserDisplayDto;
import com.assignment3.assignment3.user.dto.UserPage;
import com.assignment3.assignment3.user.mapper.UserMapper;
import com.assignment3.assignment3.user.model.ERole;
import com.assignment3.assignment3.user.model.Role;
import com.assignment3.assignment3.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    private Role findRole(ERole role) {
        return roleRepository.findByName(role).orElseThrow(
                ()-> new RuntimeException("Couldn't find role: " + role.name())
        );
    }

    public void updateUser(UpdateUserRequest request, Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find user with given id"));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        var role = roleRepository.findByName(ERole.valueOf(request.getRole()));
        role.ifPresent(user::setRole);
        userRepository.save(user);
    }

    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Couldn't find user with given id.")
        );
    }

    public Page<UserComplexDto> findAll(Integer page, Integer usersPerPage) {
        var users = userRepository.findAllByRoleOrRole(findRole(ERole.CUSTOMER),
                findRole(ERole.EMPLOYEE), PageRequest.of(page, usersPerPage));
        return users.map(userMapper::userComplexFromUser);
    }
}
