package org.project.Dao;

import org.project.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Long> {
}
