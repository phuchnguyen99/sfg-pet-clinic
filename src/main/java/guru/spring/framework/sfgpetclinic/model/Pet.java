package guru.spring.framework.sfgpetclinic.model;

import java.time.LocalDate;

public class Pet
{
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;


    public void setPetType(final PetType petType)
    {
        this.petType = petType;
    }

    public PetType getPetType()
    {
        return petType;
    }

    public void setOwner(final Owner owner)
    {
        this.owner = owner;
    }

    public Owner getOwner()
    {
        return owner;
    }

    public void setBirthDate(final LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }
}
