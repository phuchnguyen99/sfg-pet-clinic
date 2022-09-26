package guru.springframework.sfgpetclinic.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService
{
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(final OwnerRepository ownerRepository, final PetTypeRepository petTypeRepository,
                             final PetRepository petRepository)
    {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(final String lastName)
    {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll()
    {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners :: add);
        return owners;
    }

    @Override
    public Owner findById(final Long id)
    {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(final Owner object)
    {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(final Owner object)
    {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(final Long aLong)
    {
        ownerRepository.deleteById(aLong);
    }
}
