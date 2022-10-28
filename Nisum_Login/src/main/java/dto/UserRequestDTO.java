package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Phone;
import model.User;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private UUID userId;

    private String name;

    private String mail;

    private String password;

    private Set<PhoneRequestDTO> phones;

    public User build(final User user) {

        Set<Phone> phoneList = this.getPhones().stream()
                .map(phoneDto -> Phone.builder()
                        .number(phoneDto.getNumber())
                        .countryCode(phoneDto.getCountryCode())
                        .cityCode(phoneDto.getCityCode())
                        .build())
                .collect(Collectors.toSet());

        user.setName(this.name);
        user.setMail(this.mail);
        user.setPassword(this.password);
        user.setPhones(phoneList);

        user.getPhones().forEach(phone -> phone.setUser(user));

        return user;

    }
}
