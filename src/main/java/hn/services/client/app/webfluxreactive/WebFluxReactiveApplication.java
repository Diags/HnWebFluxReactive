package hn.services.client.app.webfluxreactive;

import hn.services.client.app.webfluxreactive.dao.SocieteRepo;
import hn.services.client.app.webfluxreactive.dao.TransactionRepo;
import hn.services.client.app.webfluxreactive.entities.Societe;
import hn.services.client.app.webfluxreactive.entities.Transaction;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.Instant;
import java.util.stream.Stream;
@OpenAPIDefinition(info = @Info(title = "SOCIETES TRANSACTIONS API ", version="1.0.0", description = "API SOCIETES TRANSACTIONS"),
        servers = {@Server(url="//localhost:8080")}
)
@SpringBootApplication
public class WebFluxReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxReactiveApplication.class, args);
    }

    @Bean
    CommandLineRunner start(SocieteRepo societeRepository,
                            TransactionRepo transactionRepository){
        return args->{
            societeRepository.deleteAll().subscribe(null,null,()->{
                Stream.of("BNP","SG","NATIXIS","AXA", "WARI").forEach(s->{
                    societeRepository.save(new Societe(s,s,100+Math.random()*900))
                            .subscribe(soc->{
                                System.out.println(soc.toString());
                            });
                });
                transactionRepository.deleteAll().subscribe(null,null,()->{
                    Stream.of("BNP","SG","NATIXIS","AXA","WARI").forEach(s->{
                        societeRepository.findById(s).subscribe(soc->{
                            for (int i = 0; i <10 ; i++) {
                                Transaction transaction=new Transaction();
                                transaction.setInstant(Instant.now());
                                transaction.setSociete(soc);
                                transaction.setPrice(soc.getPrice()*(1+(Math.random()*12-6)/100));
                                transactionRepository.save(transaction).subscribe(t->{
                                    System.out.println(t.toString());
                                });
                            }
                        });

                    });
                });


            });
            System.out.println(".##################################################################################################");
            System.out.println(".##################.. Start APP ...###########################");
            System.out.println(".##################################################################################################");

        };
    }

}
