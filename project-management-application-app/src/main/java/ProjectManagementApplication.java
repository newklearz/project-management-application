import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.newklearz"})
@EntityScan(basePackages = {"com.newklearz"})
@ComponentScan(basePackages = {"com.newklearz"})
public class ProjectManagementApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ProjectManagementApplication.class, args);

    }
}
