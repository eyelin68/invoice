package com.example.loginspringboot.Repository;

import com.example.loginspringboot.models.Invoice;
import com.example.loginspringboot.models.Stuff;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StuffRepository extends JpaRepository <Stuff, Long>
{
    Page<Stuff> findByCode (Integer code, Pageable pageable);
    Page<Stuff> findByNameContaining (String name, Pageable pageable);
    Page<Stuff> findByInvoiceId (Long invoiceId, Pageable pageable);

    @Transactional
    void deleteByInvoiceId (Long invoiceId);
}
