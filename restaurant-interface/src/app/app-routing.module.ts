import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ConsumerAppComponent } from './components/consumer/consumer-app/consumer-app.component';
import { MenuComponent } from './components/consumer/menu/menu.component';

import { InternalAppComponent } from './components/internal/internal-app/internal-app.component';
import { OrderListComponent } from './components/internal/order-list/order-list.component';
import { OptionsComponent } from './components/consumer/options/options.component';

import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { CartComponent } from './components/consumer/cart/cart.component';
import { AsuriteCheckoutComponent } from './components/consumer/asurite-checkout/asurite-checkout.component';
import { StatusComponent } from './components/consumer/status/status.component';
import { StatusPromptComponent } from './components/consumer/status-prompt/status-prompt.component';
import { CreditCheckoutComponent } from './components/consumer/credit-checkout/credit-checkout.component';

const routes: Routes = [
  {
    path: 'internal',
    component: InternalAppComponent,
    children: [
      {
        path: '',
        component: OrderListComponent
      },
      {
        path: '**',
        component: PageNotFoundComponent
      }
    ]
  },
  {
    path: '',
    component: ConsumerAppComponent,
    children: [
      {
        path: '',
        component: MenuComponent
      },
      {
        path: 'status',
        component: StatusPromptComponent
      },
      {
        path: 'options/:itemId',
        component: OptionsComponent
      },
      
      {
        path: 'cart',
        component: CartComponent
      },
      {
        path: 'asurite-checkout',
        component: AsuriteCheckoutComponent
      },
      {
        path: 'card-checkout',
        component: CreditCheckoutComponent
      },
      {
        path: 'status/:id',
        component: StatusComponent
      },
      {
        path: '**',
        component: PageNotFoundComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
