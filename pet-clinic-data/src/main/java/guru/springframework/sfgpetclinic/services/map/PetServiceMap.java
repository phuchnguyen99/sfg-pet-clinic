package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long>
    implements PetService
{
    @Override
    public Set<Pet> findAll()
    {
        return super.findAll();
    }

    @Override
    public void deleteById(final Long id)
    {
        super.deleteById(id);
    }

    @Override
    public void delete(final Pet object)
    {
        super.delete(object);
    }

    @Override
    public Pet save(final Pet object)
    {
        return super.save(object);
    }

    @Override
    public Pet findById(final Long id)
    {
        return super.findById(id);
    }
}
