package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "visit")
public class Visit extends BaseEntity
{
    @Column(name = "visit_date")
    private LocalDate date;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "pet_id")
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
