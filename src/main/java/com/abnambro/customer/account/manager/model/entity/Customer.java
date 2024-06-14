package com.abnambro.customer.account.manager.model.entity;

import com.abnambro.customer.account.manager.security.model.enums.TokenClaims;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userName"),
                @UniqueConstraint(columnNames = "iban")
        })
public class Customer  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String name;
    private String address;
    private String idDocNumber;
    private String dateOfBirth;
    private String password;
    private String iban;


    /**
     * Generates JWT claims for the user.
     *
     * @return Map of JWT claims.
     */
    public Map<String, Object> getClaims() {
        final Map<String, Object> claims = new HashMap<>();
        claims.put(TokenClaims.ID.getValue(), this.id);
        claims.put(TokenClaims.USERNAME.getValue(), this.userName);
        claims.put(TokenClaims.USER_FULL_NAME.getValue(), this.name);
        return claims;
    }
}
