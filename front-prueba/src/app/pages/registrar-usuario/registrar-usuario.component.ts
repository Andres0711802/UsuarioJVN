import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Usuario } from 'src/app/model/usuario.model';
import { UsuarioService} from '../../services/usuario.service';
import { Router } from '@angular/router';
import swal from'sweetalert2';

@Component({
  selector: 'app-registrar-usuario',
  templateUrl: './registrar-usuario.component.html',
  styleUrls: ['./registrar-usuario.component.css']
})
export class RegistrarUsuarioComponent implements OnInit {

  public datosForm: FormGroup;
  usuario: Usuario;

  constructor(private usuarioService : UsuarioService, private route:Router) { 
    this.datosForm = this.createFormGroup();
    this.usuario = new Usuario(0,'','','','','');
  }

  ngOnInit(): void {
  }

  createFormGroup(){
    return new FormGroup({
      nombre: new FormControl('', [Validators.required]),
      apellido: new FormControl('', [Validators.required]),
      pais: new FormControl('', [Validators.required]),
      otrosNombres: new FormControl('', [Validators.required]),
    });
  }

  onSubmit(){
    this.usuario.primerNombre = this.datosForm.get('nombre')?.value;
    this.usuario.primerApellido = this.datosForm.get('apellido')?.value;
    this.usuario.paisEmpleo = this.datosForm.get('pais')?.value;
    this.usuario.otrosNombres = this.datosForm.get('otrosNombres')?.value;

    this.usuarioService.getRegistrar(this.usuario)
    .subscribe((res: any) => {
    
      this.route.navigate(['']);
        },
    (err) => 
    swal.fire({
      icon: 'error',
      text: 'No se permiten caracteres especiales, campos vacios, numeros y longitudes superiores a 50 caracteres.',
    })
  );  
  }

}
