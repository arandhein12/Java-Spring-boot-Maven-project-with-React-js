package com.clients.Clients;

import com.clients.Clients.model.Event;
import com.clients.Clients.model.Group;
import com.clients.Clients.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final GroupRepository repository;

    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Anna Mets", "Uku Kuu", "Peep Ilm",
                "Richmond Pillerkaar").forEach(name ->
                repository.save(new Group(name))
        );

        Group djug = repository.findByName("Anna Mets");
        Event e = Event.builder().title("Client Database")
                .address("Kuu 35")
                .date(Instant.parse("2020-08-06T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
    }
}