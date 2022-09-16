package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Visit;
import org.springframework.stereotype.Service;

@Service
public interface VisitService extends CrudService<Visit, Long>
{
}
