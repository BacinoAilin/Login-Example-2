package service;

import api.IUserService;
import dto.UserRequestDTO;
import dto.UserResponseDTO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import repository.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Mono<UserResponseDTO> create(UserRequestDTO userRequestDTO) {

        return Mono.just(User.builder()
                        .name(userRequestDTO.getName())
                        .mail(userRequestDTO.getMail())
                        .password(userRequestDTO.getPassword())
                        .build())
                .flatMap(user -> Mono.just(userRepository.save(user)))
                .map(UserResponseDTO::new);

    }
}
