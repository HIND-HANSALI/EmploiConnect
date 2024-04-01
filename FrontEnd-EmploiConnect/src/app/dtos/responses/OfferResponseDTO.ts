import { AuthenticationResponseDTO } from "./AuthenticationResponseDTO";

export interface OfferResponseDTO{
    id: number;
    title: string;
    description: string;
    contrat: string;
    createdAt: Date;
    creator:AuthenticationResponseDTO;
}