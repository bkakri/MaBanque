package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Client;

public interface ClientRepository  extends JpaRepository<Client,Long>{

}
