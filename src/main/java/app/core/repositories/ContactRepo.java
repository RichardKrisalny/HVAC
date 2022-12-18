package app.core.repositories;

import app.core.entities.Contact;
import app.core.entities.Duct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contact,Integer> {
}
