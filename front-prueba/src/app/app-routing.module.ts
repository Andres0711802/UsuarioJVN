import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrarUsuarioComponent } from './pages/registrar-usuario/registrar-usuario.component';
import { DetalleComponent } from './pages/usuario/detalle/detalle.component';
import { UsuarioComponent } from './pages/usuario/usuario.component';

const routes: Routes = [
  {
    path:'',
    component:UsuarioComponent
  },
  {
    path:'registrar',
    component:RegistrarUsuarioComponent
  },
  {
    path:':id',
    component:DetalleComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
