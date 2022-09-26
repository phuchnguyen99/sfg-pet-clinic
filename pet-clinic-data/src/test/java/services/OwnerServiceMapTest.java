package services;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.PetServiceMap;
import guru.springframework.sfgpetclinic.services.map.PetTypeMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OwnerServiceMapTest
{
    private OwnerServiceMap serviceMap;
    private final Long ownerId = 1L;
    private final String lastName = "Smith";

    @BeforeEach
    public void setUp()
    {
        serviceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        serviceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    public void findAll()
    {
        Set<Owner> owners =  serviceMap.findAll();
        assertThat(1, equalTo(owners.size()));
    }

    @Test
    public void findById()
    {
        Owner owner = serviceMap.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    public void save()
    {
        Long id = 2L;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = serviceMap.save(owner);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    public void saveNoId()
    {
        Owner savedOwner = serviceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    public void delete()
    {
        serviceMap.delete(serviceMap.findById(ownerId));
        assertEquals(0, serviceMap.findAll().size());
    }

    @Test
    public void deleteById()
    {
        serviceMap.deleteById(ownerId);
        assertEquals(0, serviceMap.findAll().size());
    }

    @Test
    public void findyByLastName()
    {
        final Owner ow = serviceMap.findByLastName(lastName);
        assertEquals(ow.getLastName(), lastName);
    }

    @Test
    public void findByLastNameNonExit()
    {
        final Owner ow = serviceMap.findByLastName("Nguyen");
        assertNull(ow);
    }
}
