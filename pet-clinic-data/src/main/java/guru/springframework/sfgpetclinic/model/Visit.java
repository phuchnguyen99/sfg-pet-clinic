package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;

public class Visit extends BaseEntity
{
    private LocalDate date;
    private String description;
    private Pet pet;

    public void setDate(final LocalDate date)
    {
        this.date = date;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setPet(final Pet pet)
    {
        this.pet = pet;
    }

    public Pet getPet()
    {
        return pet;
    }
}
