import { ApplicationStatus } from "src/app/models/enums/ApplicationStatus";
import { AuthenticationResponseDTO } from "./AuthenticationResponseDTO";
import { OfferResponseDTO } from "./OfferResponseDTO";

export interface ApplicationResponseDTO{
    id:number;
    status: ApplicationStatus;
    title:String;
    profile:String;
    offer: OfferResponseDTO;
    user: AuthenticationResponseDTO;
    cv:String;
}

