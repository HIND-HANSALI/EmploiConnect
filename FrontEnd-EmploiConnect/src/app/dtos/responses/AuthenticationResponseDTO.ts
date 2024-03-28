import { Role } from "src/app/models/Role";
import { CompanyResponseDTO } from "./CompanyResponseDTO";
import { RoleResponseDTO } from "./RoleResponseDTO";

export interface AuthenticationResponseDTO {
    id:number;
    token: string;
    firstName: string;
    familyName: string;
    email: string;
    role: RoleResponseDTO;
    company:CompanyResponseDTO
}