import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './components/consumer/nav/nav.component';
import { ConsumerAppComponent } from './components/consumer/consumer-app/consumer-app.component';
import { InternalAppComponent } from './components/internal/internal-app/internal-app.component';
import { MenuComponent } from './components/consumer/menu/menu.component';
import { OptionsComponent } from './components/consumer/options/options.component';
import { CartComponent } from './components/consumer/cart/cart.component';
import { NavLinkComponent } from './components/general/nav-link/nav-link.component';
import { MenuItemComponent } from './components/consumer/menu-item/menu-item.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    ConsumerAppComponent,
    InternalAppComponent,
    MenuComponent,
    OptionsComponent,
    CartComponent,
    NavLinkComponent,
    MenuItemComponent,
    PageNotFoundComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
