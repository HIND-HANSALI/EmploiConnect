import { Role } from "src/app/models/Role";

export interface AuthenticationResponseDTO {
    id:number;
    token: string;
    firstName: string;
    familyName: string;
    email: string;
    role: Role;
    
}