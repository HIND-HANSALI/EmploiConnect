import { Role } from "src/app/models/Role";

export interface AuthenticationResponseDTO {
    token: string;
    name: string;
    email: string;
    role: Role;
}