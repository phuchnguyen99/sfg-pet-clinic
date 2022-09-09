package guru.springframework.sfgpetclinic.model;

import java.util.Set;

public class Owner extends Person
{
    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets;

    public String getAddress()
    {
        return address;
    }

    public void setAddress(final String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(final String city)
    {
        this.city = city;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(final String telephone)
    {
        this.telephone = telephone;
    }

    public Set<Pet> getPets()
    {
        return pets;
    }

    public void setPets(final Set<Pet> pets)
    {
        this.pets = pets;
    }
}
