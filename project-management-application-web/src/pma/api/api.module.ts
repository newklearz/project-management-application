import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import {  } from './configuration';
import { HttpClient } from '@angular/common/http';

import { TicketResourceService } from './api/ticketResource.service';
import { UserResourcesService } from './api/userResources.service';

@NgModule({
  imports:      [],
  declarations: [],
  exports:      [],
  providers: []
})
export class  {
    public static forRoot(configurationFactory: () => ): ModuleWithProviders {
        return {
            ngModule: ,
            providers: [ { provide: , useFactory: configurationFactory } ]
        };
    }

    constructor( @Optional() @SkipSelf() parentModule: ,
                 @Optional() http: HttpClient) {
        if (parentModule) {
            throw new Error(' is already loaded. Import in your base AppModule only.');
        }
        if (!http) {
            throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
            'See also https://github.com/angular/angular/issues/20575');
        }
    }
}
