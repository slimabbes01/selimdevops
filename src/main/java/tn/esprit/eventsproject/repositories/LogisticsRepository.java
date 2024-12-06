package tn.esprit.eventsproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.eventsproject.entities.Logistics;

import java.util.List;

public interface LogisticsRepository extends JpaRepository<Logistics, Integer> {


}