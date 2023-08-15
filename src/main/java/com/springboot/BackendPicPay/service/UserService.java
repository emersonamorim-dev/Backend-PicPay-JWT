package com.springboot.BackendPicPay.service;

import com.springboot.BackendPicPay.dto.UserDTO;
import com.springboot.BackendPicPay.model.User;
import com.springboot.BackendPicPay.model.UserType;
import com.springboot.BackendPicPay.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean authenticate(UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());
        if (optionalUser.isPresent() && bCryptPasswordEncoder.matches(userDTO.getPassword(), optionalUser.get().getPassword())) {
            return true;
        }
        return false;
    }
    


    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());


        user = userRepository.save(user);
        return convertToDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setCpf(user.getCpf());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword()); // Em sistemas reais, evite isso.
        dto.setBalance(user.getBalance());
        dto.setUserType(user.getUserType().name());
        return dto;
    }

    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFullName(dto.getFullName());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // Em sistemas reais, você deve hash isso.
        user.setBalance(dto.getBalance());
        user.setUserType(UserType.valueOf(dto.getUserType()));
        return user;
    }
}
