package com.logistics.Organisations;

import com.logistics.Organisations.OrganisationDTO;
import com.logistics.Organisations.Organisation;
import com.logistics.Helpers.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganisationService {
    private OrganisationDTO organisationDTO;
    private final String ERROR_MESSAGE="Something went wrong";

    @Autowired
    public OrganisationService(OrganisationDTO organisationDTO){
        this.organisationDTO = organisationDTO;
    }

//    create organisation
    public ResponseEntity<Object> createOrganisation(Organisation organisation){
        List<Organisation> org = organisationDTO.checkIfExist(organisation.getName(),organisation.getEmail());
        CustomResponse response = new CustomResponse();
        if(!org.isEmpty()){
            response.setResponse(0);
            response.setMessage("Organisation with the same details already exist");
            return ResponseEntity.badRequest().body(response);
        }
        else{
            try {
                Organisation newOrg = organisationDTO.save(organisation);
                response.setResponse(1);
                response.setMessage("Organisation created Successfully");
                response.setData(newOrg);
                return ResponseEntity.accepted().body(response);
            }catch (Exception ex){
                response.setResponse(0);
                response.setMessage(ERROR_MESSAGE);
                return ResponseEntity.badRequest().body(response);
            }

        }
    }


//    get Organisations
    public ResponseEntity<Object> getAllOrganisations(){
        List<Organisation> organisations = organisationDTO.findAll();
        CustomResponse response = new CustomResponse();
        response.setResponse(1);
        response.setMessage("Operation was successful");
        response.setData(organisations);
        return ResponseEntity.ok().body(response);
    }
//    get Organisation
    public ResponseEntity<Object> getOrganisation(Long id){
        CustomResponse response = new CustomResponse();
        try{
            Optional<Organisation> org = organisationDTO.findById(id);
            if(org.isPresent()){
                response.setResponse(1);
                response.setMessage("Organisation found");
                response.setData(org);
                return ResponseEntity.ok().body(response);
            }else{
                response.setResponse(0);
                response.setMessage("Organisation not found");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception ex){
            response.setResponse(0);
            response.setMessage(ERROR_MESSAGE);
            return ResponseEntity.badRequest().body(response);
        }
    }

//    update Organisation
    public ResponseEntity<Object> updateOrganisation(Long id,Organisation organisation){
        CustomResponse response = new CustomResponse();
        try{
            Optional<Organisation> org = organisationDTO.findById(id);
            if(org.isPresent()){
                organisationDTO.updateOrganisation(
                        id,
                        organisation.getName(),
                        organisation.getAddress(),
                        organisation.getEmail(),
                        organisation.getPhone(),
                        organisation.isActive());
                response.setResponse(1);
                response.setMessage("Organisation updated");
                return ResponseEntity.ok().body(response);
            }else{
                response.setResponse(0);
                response.setMessage("Organisation does not exist");
                return  ResponseEntity.badRequest().body(response);
            }
        }catch (Exception ex){

            System.out.println(ex.getMessage());
            response.setResponse(0);
            response.setMessage(ERROR_MESSAGE);
            return ResponseEntity.badRequest().body(response);
        }
    }

//    Delete Organisation
    public ResponseEntity<Object> deleteOrganisation(Long id){
        CustomResponse response = new CustomResponse();
        try{
            Optional<Organisation> org = organisationDTO.findById(id);
            if(org.isPresent()){
                organisationDTO.deleteById(id);
                response.setResponse(1);
                response.setMessage("Organisation deleted successful");
                return ResponseEntity.ok().body(response);
            }else{
                response.setResponse(0);
                response.setMessage("Organisation does not exist");
                return  ResponseEntity.badRequest().body(response);
            }
        }catch (Exception ex){
                response.setResponse(0);
                response.setMessage(ERROR_MESSAGE);
                return ResponseEntity.badRequest().body(response);
            }
        }
}
