package org.mapsa.wallet.controllers;

import org.mapsa.wallet.converters.BaseConverter;
import org.mapsa.wallet.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/wallet")
public class AbstractController<E, D, S extends
        AbstractService<E, ? extends JpaRepository<E, String>>> {
    @Autowired
    protected S service;

    @Autowired
    protected BaseConverter<E, D> converter;
}
