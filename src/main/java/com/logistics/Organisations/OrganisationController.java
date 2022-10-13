package com.logistics.Organisations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/organisation")
@RestController
public class OrganisationController {
    private OrganisationService organisationService;

    @Autowired
    public OrganisationController(OrganisationService organisationService){
        this.organisationService = organisationService;
    }

//  create Organisation
    @PostMapping
    public ResponseEntity<Object> createOrganisation(@RequestBody Organisation organisation){
    return organisationService.createOrganisation(organisation);
    }
//  get Organisations
    @GetMapping
    public ResponseEntity<Object> getAllOrganisations(){
        return organisationService.getAllOrganisations();
    }
//    get Organisation
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrganisation(@PathVariable Long id){
        return organisationService.getOrganisation(id);
    }
//    Update Organisation
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrganisation(@PathVariable Long id,@RequestBody Organisation organisation){
        return organisationService.updateOrganisation(id, organisation);
    }
//    Delete Organisation
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrganisation(@PathVariable Long id){
        return organisationService.deleteOrganisation(id);
    }
}
