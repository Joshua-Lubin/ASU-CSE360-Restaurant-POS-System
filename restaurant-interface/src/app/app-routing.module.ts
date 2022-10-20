import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ConsumerAppComponent } from './components/consumer/consumer-app/consumer-app.component';
import { MenuComponent } from './components/consumer/menu/menu.component';

import { InternalAppComponent } from './components/internal/internal-app/internal-app.component';
import { OrderListComponent } from './components/internal/order-list/order-list.component';

const routes: Routes = [
  {
    path: '',
    component: ConsumerAppComponent,
    children: [
      {
        path: '',
        component: MenuComponent
      }
    ]
  },
  {
    path: 'internal',
    component: InternalAppComponent,
    children: [
      {
        path: '',
        component: OrderListComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
