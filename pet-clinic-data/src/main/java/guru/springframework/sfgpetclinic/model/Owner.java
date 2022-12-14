package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person
{
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Builder
    public Owner(final Long id, final String firstName, final String lastName, final String address,
                 final String city, final String telephone, final Set<Pet> pets)
    {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if(pets != null)
        {
            this.pets = pets;
        }
    }

    public void addPet(final Pet pet)
    {
        pets.add(pet);
    }

    public Pet getPet(final String name)
    {
        return getPet(name, false);
    }

    public Pet getPet(String name, boolean ignoreNew)
    {
        final String n = name.toLowerCase();
        for(Pet pet : pets)
        {
            if(!ignoreNew || !pet.isNew())
            {
                String compName = pet.getName().toLowerCase();
                if(compName.equalsIgnoreCase(n))
                {
                    return pet;
                }
            }
        }
        return null;
    }
}
