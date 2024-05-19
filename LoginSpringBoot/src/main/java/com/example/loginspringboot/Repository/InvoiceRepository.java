package com.example.loginspringboot.Repository;


import com.example.loginspringboot.models.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface InvoiceRepository extends JpaRepository <Invoice, Long>
{
   Page<Invoice> findByDate(Date date, Pageable pageable);
}
