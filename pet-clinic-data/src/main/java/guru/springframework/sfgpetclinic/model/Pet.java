package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;

public class Pet extends BaseEntity
{
    private String name;
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

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
