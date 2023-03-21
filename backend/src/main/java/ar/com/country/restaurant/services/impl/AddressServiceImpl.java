package ar.com.country.restaurant.services.impl;

import ar.com.country.restaurant.dao.entities.Address;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.exceptions.AddressNotFoundException;
import ar.com.country.restaurant.repositories.AddressRepository;
import ar.com.country.restaurant.services.AddressService;
import ar.com.country.restaurant.services.UserService;
import ar.com.country.restaurant.util.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final UserService userService;
    private final AddressRepository addressRepository;

    @Override
    public List<Address> getAddressesOfUser(Long userId) {
        return addressRepository.findAddressesByUserId(userId);
    }

    @Override
    public Address getAddressById(Long userId, Long addressId) {
        return addressRepository
                .findAddressByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));
    }

    @Override
    public Address addAddressToUser(Long userId, Address address) {
        User user = userService.getUserById(userId);
        user.addAddress(address);
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Address updateAddress(Long userId, Long addressId, Address updatedAddress) {
        Address oldAddress = getAddressById(userId, addressId);
        BeanUtils.copyProperties(updatedAddress, oldAddress);
        return addressRepository.save(oldAddress);
    }

    @Override
    public Address deleteAddress(Long userId, Long addressId) {
        Address addressToDelete = getAddressById(userId, addressId);
        addressRepository.deleteById(addressId);
        return addressToDelete;
    }

}
