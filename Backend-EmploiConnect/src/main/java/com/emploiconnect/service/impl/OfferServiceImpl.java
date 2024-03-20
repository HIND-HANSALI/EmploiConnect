package com.emploiconnect.service.impl;

import com.emploiconnect.dto.request.OfferRequestDto;
import com.emploiconnect.dto.response.OfferResponseDto;
import com.emploiconnect.entity.Offer;
import com.emploiconnect.handler.exception.ResourceNotFoundException;
import com.emploiconnect.repository.OfferRepository;
import com.emploiconnect.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    @Override
    public OfferResponseDto createOffer(OfferRequestDto offerDto) {
        // Map the OfferRequestDto to the Offer entity
        Offer offer = modelMapper.map(offerDto, Offer.class);

        // Save the Offer entity to the database
        Offer savedOffer = offerRepository.save(offer);

        // Map the saved Offer entity back to an OfferResponseDto
        OfferResponseDto offerResponseDto = modelMapper.map(savedOffer, OfferResponseDto.class);

        // Return the OfferResponseDto
        return offerResponseDto;
    }

    @Override
    public List<OfferResponseDto> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream()
                .map(offer->modelMapper.map(offer, OfferResponseDto.class))
                .collect(Collectors.toList());
    }
  /*  private OfferResponseDto convertToDto(Offer offer) {
        return modelMapper.map(offer, OfferResponseDto.class);
    }*/
  @Override
  public OfferResponseDto getOfferById(Long id){

      Offer offer=offerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offer id " + id + " not found"));
      return modelMapper.map(offer, OfferResponseDto.class);

  }


    @Override
    public OfferResponseDto updateOffer(OfferRequestDto offreDto,Long id){

        // Retrieve the existing offer from the database based on its ID
        Offer existingOffer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + id));

        // Update the fields of the existing offer with the data from the OfferRequestDto
        /*modelMapper.map(offreDto, existingOffer);
        existingOffer.setUpdatedAt(new Date());*/

        // Update the fields of the existing offer with the data from the OfferRequestDto
        existingOffer.setTitle(offreDto.getTitle());
        existingOffer.setDescription(offreDto.getDescription());
        existingOffer.setContrat(offreDto.getContrat());
        existingOffer.setUpdatedAt(new Date());

        // Save the updated offer back to the database
        Offer updatedOffer = offerRepository.save(existingOffer);

        // Map the updated offer to an OfferResponseDto
        OfferResponseDto offerResponseDto = modelMapper.map(updatedOffer, OfferResponseDto.class);

        // Return the OfferResponseDto
        return offerResponseDto;
    }

    @Override
    public void deleteOffer(Long id) {

        Offer existingOffer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + id));

        offerRepository.delete(existingOffer);
    }
}
