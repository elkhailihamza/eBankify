package org.project.Dao;

import org.project.Entity.Invoice;
import org.project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByOwner_Id(long ownerId);
}
