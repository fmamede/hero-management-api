package com.github.fmd00.heroesapi;

import com.github.fmd00.heroesapi.repository.HeroesRepository;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.fmd00.heroesapi.constants.HeroesConstants.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
public class HeroesapiApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    HeroesRepository heroesRepository;

    @Test
    public void getOneHeroeById() {
        webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "4")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void getOneHeronotFound() {
        webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "5")
                .exchange()
                .expectStatus().isNotFound();
    }


    @Test
    public void deleteHero() {
        webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "4")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Void.class);
    }
}


