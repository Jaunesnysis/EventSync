package com.example.ibmtask.repository;

import com.example.ibmtask.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
