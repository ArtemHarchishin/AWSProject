package com.akharchyshyn.aws.repository;

import com.akharchyshyn.aws.entity.Image;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM image ORDER BY RAND() limit 1;")
    Optional<Image> findRandomImage();

}
