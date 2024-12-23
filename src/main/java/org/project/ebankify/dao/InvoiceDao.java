package org.project.ebankify.dao;

import org.project.ebankify.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByOwner_Id(long ownerId);
}
