package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.*;
import com.homecook.homecookentity.repository.ShippingModeRepository;
import com.homecook.homecookentity.type.ShippingModeType;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "shippingModeService")
public class DefaultShippingModeService implements ShippingService
{
    private ShippingModeRepository shippingModeRepository;

    @Autowired
    public DefaultShippingModeService(ShippingModeRepository shippingModeRepository)
    {
        this.shippingModeRepository = shippingModeRepository;
    }

    @Override
    public ShippingModeEntity getShippingModeForCode(String code)
    {
        Specification<ShippingModeEntity> sf = (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("code"), code),
                criteriaBuilder.equal(root.get("active"), true)
        );
        final Optional<ShippingModeEntity> shippingMode = shippingModeRepository.findOne(sf);

        if (shippingMode.isPresent())
        {
            return shippingMode.get();
        }
        else
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Shipping mode with code " + code + " not found.");
        }
    }

    @Override
    public List<String> getAllCVSCodes()
    {
        Specification<ShippingModeEntity> sf = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("shippingModeType"), ShippingModeType.CVS);
        final List<ShippingModeEntity> allByShippingType = shippingModeRepository.findAll(sf);
        return allByShippingType.stream().map(ShippingModeEntity::getCode).collect(Collectors.toList());
    }

    @Override
    public List<ShippingModeEntity> getSupportedShippingModesForCart(CartEntity cart)
    {
        validateParameterNotNullStandardMessage("cart", cart);

        final List<ProductEntity> products =
                cart.getLineItems().stream().map(CartLineItemEntity::getProduct).collect(Collectors.toList());

        final Set<ShippingModeEntity> firstProductSupportedShippingModes = products.get(0).getShippingModes();

        if (firstProductSupportedShippingModes == null)
        {
            return Collections.emptyList();
        }

        Set<ShippingModeEntity> intersection = new HashSet<>(firstProductSupportedShippingModes);

        for (int i = 0; i < products.size(); i++)
        {
            final Set<ShippingModeEntity> productSupportedShippingModes =
                    products.get(i).getShippingModes();

            if (productSupportedShippingModes == null)
                return Collections.emptyList();

            intersection.retainAll(productSupportedShippingModes);
        }

        // make sure all shipping modes are active
        intersection.retainAll(getAllEnabledShippingModes());

        return new ArrayList<>(intersection);
    }

    @Override
    public List<AddressEntity> getSupportedShippingAddressesForCart(CartEntity cart)
    {
        validateParameterNotNullStandardMessage("cart", cart);

        final List<ShippingModeEntity> supportedShippingModes = getSupportedShippingModesForCart(cart);

        final CustomerEntity customer = cart.getCustomer();

        final List<AddressEntity> addresses = customer.getAddresses()
                .stream()
                .filter(address -> supportedShippingModes.contains(address.getShippingMode()))
                .collect(Collectors.toList());

        return addresses;
    }

    protected Set<ShippingModeEntity> getAllEnabledShippingModes()
    {
        final List<ShippingModeEntity> activeShippingModes = shippingModeRepository.findAllByActiveTrue();
        return new HashSet<>(activeShippingModes);
    }
}
