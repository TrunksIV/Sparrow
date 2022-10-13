package com.logistics.Organisations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrganisationDTO extends JpaRepository<Organisation,Long> {

    @Query("SELECT org FROM organisations org WHERE org.name=?1 AND org.email=?2")
    List<Organisation> checkIfExist(String name, String email);

    @Modifying
    @Transactional
    @Query("UPDATE organisations org SET org.name=?2,org.address=?3,org.email=?4,org.phone=?5,active=?6 WHERE org.orgId=?1 ")
    int updateOrganisation(Long id,String name,String address,String email,String phone,boolean active);
}
