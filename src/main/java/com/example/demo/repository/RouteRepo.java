package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Route;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {

}
