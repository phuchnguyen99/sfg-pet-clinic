package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner
{
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;
    private final PetService petService;

    public DataLoader(final OwnerService ownerService, final VetService vetService,
                      final PetTypeService petTypeService, final SpecialityService specialityService,
                      final VisitService visitService, final PetService petService)
    {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
        this.petService = petService;
    }
    @Override
    public void run(final String... args)
        throws Exception
    {
        int count = petTypeService.findAll().size();
        if(count == 0)
        {
            loadData();
        }
    }

    private void loadData()
    {
        PetType dog = new PetType();
        dog.setName("betty");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("lunar");
        PetType saveCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialityService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialityService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialityService.save(dentistry);


        Owner owner1 = new Owner();
        owner1.setFirstName("Phuc");
        owner1.setLastName("Nguyen");
        owner1.setAddress("1234 Crestview Dr");
        owner1.setTelephone("2482381111");
        owner1.setCity("Hanoi");

        ownerService.save(owner1);

        Pet phucsPet = new Pet();
        phucsPet.setPetType(saveCatPetType);
        phucsPet.setOwner(owner1);
        phucsPet.setBirthDate(LocalDate.now());
        phucsPet.setName("bun");
        owner1.getPets().add(phucsPet);

        Owner owner2 = new Owner();
        owner2.setFirstName("Duc");
        owner2.setLastName("Ha");
        ownerService.save(owner2);

        Pet dotCat = new Pet();
        dotCat.setName("Dots");
        dotCat.setOwner(owner2);
        dotCat.setBirthDate(LocalDate.now());
        dotCat.setPetType(saveCatPetType);
        owner2.getPets().add(dotCat);
        petService.save(dotCat);
        System.out.println("Load Owners....");

        Visit catVisit = new Visit();
        catVisit.setPet(dotCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        visitService.save(catVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Ha");
        vet1.setLastName("Pham");
        vet1.getSpecialties().add(savedRadiology);

        Vet vet2 = new Vet();
        vet2.setFirstName("Anh");
        vet2.setLastName("Vuong");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet1);
        vetService.save(vet2);
        System.out.println("loaded Vets...");
    }
}
