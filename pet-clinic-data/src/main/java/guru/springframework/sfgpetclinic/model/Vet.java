package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person
{
    private Set<Specialty> specialties = new HashSet<>();

    public Set<Specialty> getSpecialties()
    {
        return specialties;
    }

    public void setSpecialties(final Set<Specialty> specialties)
    {
        this.specialties = specialties;
    }
}
