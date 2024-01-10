package com.example.backend.repository;

import java.util.Optional;

import com.example.backend.model.platform.Platform;
import com.example.backend.model.platform.EPlatformName;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IPlatformRepository extends JpaRepository<Platform, Long> {

    Optional<Platform> findPlatformByPlatformName(final EPlatformName platformName);

}