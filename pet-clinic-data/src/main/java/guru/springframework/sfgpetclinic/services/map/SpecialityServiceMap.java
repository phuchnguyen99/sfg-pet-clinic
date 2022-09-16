package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class SpecialityServiceMap extends AbstractMapService<Specialty, Long> implements SpecialityService
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
