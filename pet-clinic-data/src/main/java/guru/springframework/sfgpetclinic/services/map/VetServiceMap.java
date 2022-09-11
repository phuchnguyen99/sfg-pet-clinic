package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long>
    implements VetService
{
    private final SpecialityService specialityService;

    public VetServiceMap(final SpecialityService specialityService)
    {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll()
    {
        return super.findAll();
    }

    @Override
    public void deleteById(final Long id)
    {
        super.deleteById(id);
    }

    @Override
    public void delete(final Vet object)
    {
        super.delete(object);
    }

    @Override
    public Vet save(final Vet object)
    {
        if(object.getSpecialties().size() > 0)
        {
            for(final Specialty s : object.getSpecialties())
            {
                if(s.getId() == null)
                {
                    Specialty specialty = specialityService.save(s);
                    s.setId(specialty.getId());
                }
            }
        }
        return super.save(object);
    }

    @Override
    public Vet findById(final Long id)
    {
        return super.findById(id);
    }
}
