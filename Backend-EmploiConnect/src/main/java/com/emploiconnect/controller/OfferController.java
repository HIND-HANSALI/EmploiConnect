package com.emploiconnect.controller;

import com.emploiconnect.dto.request.OfferRequestDto;
import com.emploiconnect.dto.response.OfferResponseDto;
import com.emploiconnect.handler.exception.ResourceNotFoundException;
import com.emploiconnect.handler.response.ResponseMessage;
import com.emploiconnect.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offers")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public ResponseEntity<List<OfferResponseDto>> getAllOffers() {
        List<OfferResponseDto> offers = offerService.getAllOffers();
        if(offers.isEmpty()) {
            return ResponseMessage.notFound("Offer not found");
        }else {
            //return ResponseMessage.ok("Success" ,offers );
            return new ResponseEntity<>(offers, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<OfferResponseDto> getOfferById(@PathVariable Long id){
        try {
            OfferResponseDto offer = offerService.getOfferById(id);
            return ResponseEntity.ok(offer);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    //@PreAuthorize("hasAnyAuthority('CREATE_OFFER')")
    @PreAuthorize("hasRole('ROLE_RECRUITER') || hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<OfferResponseDto> createOffer(@Valid @RequestBody OfferRequestDto offerRequestDto) {
        OfferResponseDto createdOffer = offerService.createOffer(offerRequestDto);

        if(createdOffer==null){
            return ResponseMessage.badRequest("Offer not created");
        }else{
            return ResponseMessage.created("Offer created successfully" ,createdOffer );
        }

    }
    @PreAuthorize("hasRole('ROLE_RECRUITER') || hasRole('ROLE_SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<OfferResponseDto> updateOffer(@Valid @RequestBody OfferRequestDto offerRequestDto, @PathVariable Long id) {
        System.out.println(offerRequestDto);
        OfferResponseDto updatedOffer = offerService.updateOffer(offerRequestDto, id);
        if(updatedOffer==null){
            return ResponseMessage.badRequest("Offer not updated");
        }else{
            return ResponseMessage.ok("Offer updated successfully" ,updatedOffer );
        }
    }
    @PreAuthorize("hasRole('ROLE_RECRUITER') || hasRole('ROLE_SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {

         offerService.deleteOffer(id);
        //return ResponseEntity.ok("Competition deleted successfully");
    }
}
