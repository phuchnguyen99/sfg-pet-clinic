package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
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
    private PetTypeService petTypeService;
    public DataLoader(final OwnerService ownerService, final VetService vetService,
                      final PetTypeService petTypeService)
    {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }
    @Override
    public void run(final String... args)
        throws Exception
    {
        PetType dog = new PetType();
        dog.setName("betty");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("lunar");
        PetType saveCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Phuc");
        owner1.setLastName("Nguyen");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Duc");
        owner2.setLastName("Ha");

        ownerService.save(owner2);
        System.out.println("Load Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Ha");
        vet1.setLastName("Pham");

        Vet vet2 = new Vet();
        vet2.setFirstName("Anh");
        vet2.setLastName("Vuong");

        vetService.save(vet1);
        vetService.save(vet2);
        System.out.println("loaded Vets...");
    }
}
