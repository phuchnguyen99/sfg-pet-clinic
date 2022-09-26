package guru.spring.framework.sfgpetclinic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SfgPetClinicApplicationTests.class)
public class SfgPetClinicApplicationTests
{

    @Test
    public void contextLoads()
    {
        assertThat(1, equalTo(2));
    }
}
