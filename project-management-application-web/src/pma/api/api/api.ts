export * from './ticketResource.service';
import { TicketResourceService } from './ticketResource.service';
export * from './userResources.service';
import { UserResourcesService } from './userResources.service';
export const APIS = [TicketResourceService, UserResourcesService];
