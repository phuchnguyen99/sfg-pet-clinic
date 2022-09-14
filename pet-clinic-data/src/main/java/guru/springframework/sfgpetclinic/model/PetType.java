package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class PetType extends BaseEntity
{
    @Column(name = "name")
    private String name;

    public PetType(final String name)
    {
        this.name = name;
    }

    public PetType()
    {

    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
