package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable
{
    private Long id;

    public void setId(final Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
}
