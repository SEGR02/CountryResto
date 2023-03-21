package ar.com.country.restaurant.repositories;

import ar.com.country.restaurant.dao.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.user.id = ?1")
    List<Address> findAddressesByUserId(Long userId);

    @Query("select a from Address a where a.id = ?1 and a.user.id = ?2")
    Optional<Address> findAddressByIdAndUserId(Long addressId, Long userId);

}
