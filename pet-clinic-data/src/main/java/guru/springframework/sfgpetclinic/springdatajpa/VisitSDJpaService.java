package guru.springframework.sfgpetclinic.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService
{
    private final VisitRepository visitRepository;

    public VisitSDJpaService(final VisitRepository visitRepository)
    {
        this.visitRepository = visitRepository;
    }
    @Override
    public Set<Visit> findAll()
    {
        final Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(final Long aLong)
    {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(final Visit object)
    {
        return visitRepository.save(object);
    }

    @Override
    public void delete(final Visit object)
    {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(final Long aLong)
    {
        visitRepository.deleteById(aLong);
    }
}
