export * from './ticket.service';
import { TicketService } from './ticket.service';
export * from './userResources.service';
import { UserResourcesService } from './userResources.service';
export const APIS = [TicketService, UserResourcesService];
