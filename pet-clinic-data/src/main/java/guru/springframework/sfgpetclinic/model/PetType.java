package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "types")
public class PetType extends BaseEntity
{
    @Column(name = "name")
    private String name;

    @Builder
    public PetType(final Long id, final String name)
    {
        super(id);
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
