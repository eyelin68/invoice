package com.example.loginspringboot.service;


import com.example.loginspringboot.Repository.InvoiceRepository;
import com.example.loginspringboot.Repository.StuffRepository;
import com.example.loginspringboot.models.Invoice;
import com.example.loginspringboot.models.Stuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StuffService
{
    @Autowired
    StuffRepository stuffRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Page<Stuff> getAllStuffs(int page, int size)
    {


        PageRequest pageRequest= PageRequest.of(page, size);
        Page<Stuff> stuffs= stuffRepository.findAll(pageRequest);
        return stuffs;
        //return stuffRepository.findAll();
    }

    public Stuff getStuffById(Long id)
    {
        return stuffRepository.findById(id).orElse(null);
    }

    public Stuff createStuff(Stuff stuff,Long invoiceId)
    {
        validateStuffCode(stuff.getCode());
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(()->new RuntimeException("Invoice not found"));
        stuff.setInvoice(invoice);
        return stuffRepository.save(stuff);
    }
    private void validateStuffCode(int code) {
        if (code < -2147483647 || code > 2147483647) {
            throw new IllegalArgumentException("The code is not in the valid range");
        }
    }

    public Stuff udateStuff(Long id,Stuff stuff)
    {
        Stuff existingStuff = stuffRepository.findById(id).orElse(null);
        if (existingStuff != null)
        {
            existingStuff.setName(stuff.getName());
            existingStuff.setDescription(stuff.getDescription());
            existingStuff.setPrimaryPrice(stuff.getPrimaryPrice());
            existingStuff.setCode(stuff.getCode());
            return stuffRepository.save(existingStuff);
        }
        else
        {
            return null;
        }
    }
    public void deleteStuff(Long id)
    {
        stuffRepository.deleteById(id);
    }
}
