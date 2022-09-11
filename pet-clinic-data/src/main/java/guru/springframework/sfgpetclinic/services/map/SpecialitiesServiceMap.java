package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.services.SpecialitiesService;

import java.util.Set;

public class SpecialitiesServiceMap extends AbstractMapService<Specialty, Long> implements SpecialitiesService
{
    @Override
    public Set<Specialty> findAll()
    {
        return super.findAll();
    }

    @Override
    public void deleteById(final Long id)
    {
        super.deleteById(id);
    }

    @Override
    public void delete(final Specialty object)
    {
        super.delete(object);
    }

    @Override
    public Specialty save(final Specialty object)
    {
        return super.save(object);
    }

    @Override
    public Specialty findById(final Long id)
    {
        return super.findById(id);
    }
}
