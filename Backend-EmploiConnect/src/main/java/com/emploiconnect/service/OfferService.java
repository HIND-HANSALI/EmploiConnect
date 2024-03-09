package com.emploiconnect.service;

import com.emploiconnect.dto.request.OfferRequestDto;
import com.emploiconnect.dto.response.OfferResponseDto;
import com.emploiconnect.entity.Offer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OfferService {
    OfferResponseDto createOffer(OfferRequestDto offreDto);
    public List< OfferResponseDto> getAllOffers();

}
