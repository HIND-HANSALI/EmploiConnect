package com.emploiconnect.service.impl;

import com.emploiconnect.dto.request.OfferRequestDto;
import com.emploiconnect.dto.response.OfferResponseDto;
import com.emploiconnect.entity.Offer;
import com.emploiconnect.entity.User;
import com.emploiconnect.handler.exception.ResourceNotFoundException;
import com.emploiconnect.repository.OfferRepository;
import com.emploiconnect.repository.UserRepository;
import com.emploiconnect.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public OfferResponseDto createOffer(OfferRequestDto offerDto) {
        //Retrieve the Authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the email or username of the authenticated user
        String userEmail = authentication.getName();
        User authenticatedUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + userEmail));

        // Map the OfferRequestDto to the Offer entity
        Offer offer = modelMapper.map(offerDto, Offer.class);

        offer.setCreator(authenticatedUser);

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
    public OfferResponseDto updateOffer(OfferRequestDto offerDto,Long id){

        // Retrieve the existing offer from the database based on its ID
        Offer existingOffer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + id));

        // Update the fields of the existing offer with the data from the OfferRequestDto
        /*modelMapper.map(offerDto, existingOffer);
        existingOffer.setUpdatedAt(new Date());*/

        // Update the fields of the existing offer with the data from the OfferRequestDto
        existingOffer.setTitle(offerDto.getTitle());
        existingOffer.setDescription(offerDto.getDescription());
        existingOffer.setContrat(offerDto.getContrat());
        existingOffer.setUpdatedAt(new Date());

        // Retrieve the Authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userEmail = authentication.getName();
        User authenticatedUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + userEmail));

        existingOffer.setCreator(authenticatedUser);

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
