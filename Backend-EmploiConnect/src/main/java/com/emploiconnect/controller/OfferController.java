package com.emploiconnect.controller;

import com.emploiconnect.dto.request.OfferRequestDto;
import com.emploiconnect.dto.response.OfferResponseDto;
import com.emploiconnect.handler.response.ResponseMessage;
import com.emploiconnect.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
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
            return ResponseMessage.ok("Success" ,offers );
        }
    }
    @PostMapping
    public ResponseEntity<OfferResponseDto> createOffer(@RequestBody OfferRequestDto offerRequestDto) {
        OfferResponseDto createdOffer = offerService.createOffer(offerRequestDto);

        if(createdOffer==null){
            return ResponseMessage.badRequest("Offer not created");
        }else{
            return ResponseMessage.created("Offer created successfully" ,createdOffer );
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<OfferResponseDto> updateOffer(@RequestBody OfferRequestDto offerRequestDto, @PathVariable Long id) {
        System.out.println(offerRequestDto);
        OfferResponseDto updatedOffer = offerService.updateOffer(offerRequestDto, id);
        if(updatedOffer==null){
            return ResponseMessage.badRequest("Offer not updated");
        }else{
            return ResponseMessage.ok("Offer updated successfully" ,updatedOffer );
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOffer(@PathVariable Long id) {

        offerService.deleteOffer(id);
        return ResponseEntity.ok("Competition deleted successfully");
    }
}
