package guru.springframework.sfgpetclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pets")
public class Pet extends BaseEntity
{
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();
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
