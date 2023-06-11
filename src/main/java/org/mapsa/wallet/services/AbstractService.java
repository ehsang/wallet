package org.mapsa.wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class AbstractService<E, R extends JpaRepository<E, String>> {
    @Autowired
    protected R repository;
}
