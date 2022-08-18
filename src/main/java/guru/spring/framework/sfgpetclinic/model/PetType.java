package guru.spring.framework.sfgpetclinic.model;

public class PetType
{
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
