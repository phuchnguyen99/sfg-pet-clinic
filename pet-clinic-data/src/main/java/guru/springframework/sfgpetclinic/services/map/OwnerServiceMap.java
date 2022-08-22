package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.CrudService;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner, Long>
    implements CrudService<Owner, Long>
{
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
        return super.save(object.getId(), object);
    }

    @Override
    public Owner findById(final Long id)
    {
        return super.findById(id);
    }
}
