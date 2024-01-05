package com.example.backend.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.example.backend.model.platform.Platform;
import com.example.backend.model.platform.EPlatformName;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IPlatformRepository extends JpaRepository<Platform, Long> {

    // Returns the corresponding Platform object with this name.
    Optional<Platform> findPlatformByPlatformName(final EPlatformName platformName);
}
