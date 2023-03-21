package ar.com.country.restaurant.dao.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column
    private String street;

    @Column(nullable = false)
    private String number;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String country;

    @Column(name = "zip_code")
    private String zipCode;

    @Column
    private String clarifications;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
