package services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.springdatajpa.OwnerSDJpaService;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OwnerSDJpaServiceTest
{
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService jpaService;
    private final Long ownerId = 1L;
    private final String lastName = "Smith";
    private Owner owner;

    @BeforeEach
    public void setUp()
    {
        owner = Owner.builder().id(ownerId).lastName(lastName).build();
    }
    @Test
    public void findByLastName()
    {
        Owner returnedOwner = Owner.builder().id(ownerId).lastName(lastName).build();
        when(jpaService.findByLastName(any())).thenReturn(returnedOwner);
        final Owner owner = jpaService.findByLastName(lastName);
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    public void findAll()
    {
        Set<Owner> owners = new HashSet<>();
        owners.add(owner);
        when(jpaService.findAll()).thenReturn(owners);
        final Set<Owner> returnedOwners = jpaService.findAll();
        assertEquals(1, returnedOwners.size());
    }

    @Test
    public void findById()
    {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        final Owner returnedOwner = jpaService.findById(ownerId);
        assertEquals(ownerId, returnedOwner.getId());
    }

    @Test
    public void save()
    {
        when(ownerRepository.save(any())).thenReturn(owner);
        final Owner savedOwner = jpaService.save(owner);
        assertEquals(owner, savedOwner);
        assertEquals(ownerId, savedOwner.getId());
    }

    @Test
    public void delete()
    {
        jpaService.delete(owner);
        verify(ownerRepository).delete(any());
    }

    @Test
    public void deleteById()
    {
        jpaService.deleteById(ownerId);
        verify(ownerRepository).deleteById(anyLong());
    }
}
