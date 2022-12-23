import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Usuario } from 'src/app/model/usuario.model';
import { UsuarioService} from '../../../services/usuario.service';
import { ActivatedRoute, Params } from '@angular/router';
import swal from'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-detalle',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {

  
  public datosForm: FormGroup;
  usuario: Usuario;

  id:any;

  constructor(private usuarioService : UsuarioService, private rutaActiva: ActivatedRoute
    , private route:Router) { 
    this.datosForm = this.createFormGroup();
    this.usuario = new Usuario(0, '','','','','');

    this.rutaActiva.params.subscribe(
      (params: Params) => {
        this.id = params['id'];
      }
    );

    this.consultarUsuario();

  }

  ngOnInit(): void {
    
  }

  consultarUsuario(){
    this.usuarioService.getUsuarioId(this.id)
    .subscribe((res: Usuario) => {
      this.datosForm.get('nombre')?.setValue(res.primerNombre);
    this.datosForm.get('apellido')?.setValue(res.primerApellido);
     this.datosForm.get('pais')?.setValue(res.paisEmpleo);
    this.datosForm.get('otrosNombres')?.setValue(res.otrosNombres);
        },
    (err) => 
    swal.fire({
      icon: 'error',
      text: 'No se permiten caracteres especiales, campos vacios, numeros y longitudes superiores a 50 caracteres.',
    })
  );  
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

    this.usuarioService.editar(this.id, this.usuario)
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
