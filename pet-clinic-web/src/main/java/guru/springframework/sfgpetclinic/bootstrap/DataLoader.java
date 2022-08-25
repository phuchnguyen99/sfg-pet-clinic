package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner
{
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(final OwnerService ownerService, final VetService vetService)
    {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }
    @Override
    public void run(final String... args)
        throws Exception
    {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Phuc");
        owner1.setLastName("Nguyen");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Duc");
        owner2.setLastName("Ha");

        ownerService.save(owner2);
        System.out.println("Load Owners....");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Ha");
        vet1.setLastName("Pham");

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Anh");
        vet2.setLastName("Vuong");

        vetService.save(vet1);
        vetService.save(vet2);
        System.out.println("loaded Vets...");
    }
}
