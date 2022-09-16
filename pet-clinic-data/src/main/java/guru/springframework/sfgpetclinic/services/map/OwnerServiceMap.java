package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long>
    implements OwnerService
{
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(final PetTypeService petTypeService, final PetService petService)
    {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll()
    {
        return super.findAll();
    }

    @Override
    public void deleteById(final Long id)
    {
        super.deleteById(id);
    }

    @Override
    public void delete(final Owner object)
    {
        super.delete(object);
    }

    @Override
    public Owner save(final Owner object)
    {
        if(object != null)
        {
            if(object.getPets() != null)
            {
                for(final Pet pet : object.getPets())
                {
                    if(pet.getPetType() != null)
                    {
                        if(pet.getPetType().getId() == null)
                        {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    }
                    else
                    {
                        throw new RuntimeException("Pet Type is required.");
                    }
                    if(pet.getId() == null)
                    {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                }
            }
        }
        return  object != null ?  super.save(object) : null;
    }

    @Override
    public Owner findById(final Long id)
    {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(final String lastName)
    {
        return null;
    }
}
