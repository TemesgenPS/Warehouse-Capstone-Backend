package com.capstone.warehouse.service;

import com.capstone.warehouse.entity.Supplier;
import com.capstone.warehouse.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Integer id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    public Supplier createSupplier(Supplier supplier) {
        if (supplier.getEmail() != null && supplierRepository.existsByEmail(supplier.getEmail())) {
            throw new RuntimeException("Supplier with this email already exists: " + supplier.getEmail());
        }
        supplier.setCreatedAt(LocalDateTime.now());
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Integer id, Supplier updated) {
        Supplier existing = getSupplierById(id);
        existing.setName(updated.getName());
        existing.setContactName(updated.getContactName());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setAddress(updated.getAddress());
        return supplierRepository.save(existing);
    }

    public void deleteSupplier(Integer id) {
        supplierRepository.deleteById(id);
    }
}