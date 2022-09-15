package guru.springframework.sfgpetclinic.springdatajpa;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService implements SpecialityService
{
    private final SpecialtyRepository specialtyRepository;
    public SpecialtySDJpaService(final SpecialtyRepository specialtyRepository)
    {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll()
    {
        final Set<Specialty> specialties = new HashSet<>();
        specialtyRepository.findAll().forEach(specialties :: add);
        return specialties;
    }

    @Override
    public Specialty findById(final Long aLong)
    {
        return specialtyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Specialty save(final Specialty object)
    {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(final Specialty object)
    {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(final Long aLong)
    {
        specialtyRepository.deleteById(aLong);
    }
}
