package ar.com.country.restaurant.services;

import ar.com.country.restaurant.dao.entities.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddressesOfUser(Long userId);

    Address getAddressById(Long userId, Long addressId);

    Address addAddressToUser(Long userId, Address address);

    Address updateAddress(Long userId, Long addressId, Address updatedAddress);

    Address deleteAddress(Long userId, Long addressId);

}
