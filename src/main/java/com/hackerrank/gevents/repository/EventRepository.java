package com.hackerrank.gevents.repository;

import com.hackerrank.gevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByActorIdOrderByIdAsc(Integer actorId);
    List<Event> findByRepoIdOrderByIdAsc(Integer repoId);
}