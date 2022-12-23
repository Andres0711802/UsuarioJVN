import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../model/usuario.model';
import { pageable} from '../../model/pageable.model';
import { UsuarioService} from '../../services/usuario.service';
import { Router } from '@angular/router';
import swal from'sweetalert2';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  
  public listaUsuarios : Usuario[] =[];
  cantidad : number[]=[];
  public estadoNextdeshabilitado= false;
  public estadoNext= true;
  maximasPage:number=5;  
  page:pageable={
    number:0,
    size:10,
    totalPages :0,
    totalElements:0,
    paginaActual : 0   
  }
  
  constructor(
    private usuarioService : UsuarioService,
    private router: Router
  ) { }

  
  ngOnInit(): void {
    this.getUsuarios();
  }

  getUsuarios(){        
           
    this.usuarioService.getUsuarios(this.page)
    .subscribe((res: any) => {                        
        this.listaUsuarios=res.content;
        this.page=res;
      this.page.paginaActual=this.page.number +1;          
      let pagesN = this.maximasPage <= this.page.totalPages ? this.maximasPage : this.page.totalPages
      let s;
      if(this.page.number > 0){
         s=this.cantidad[pagesN - 1]              
      }else{
         s=this.cantidad[pagesN]              
      }      
        if(this.page.totalPages != s){                   
          for (let i = 0; i < pagesN; i++) {                                  
            this.cantidad[i]=this.page.paginaActual++;                                                                             
          }  
        }                                                                        
      },
      (err) => console.log(err)
    );  
  }

  setNext(){ 
    this.page.number++;      
    let cont = this.page.number + 1 ;
    let s =this.cantidad[this.maximasPage - 1] + 1;                       
    if(this.page.totalPages > cont){
        if(this.page.totalPages == s){        
          this.cantidad.shift(); 
          this.cantidad.push(s);
        }
    }else{
      this.estadoNextdeshabilitado=true;
    }
    this.getUsuarios();           
  }

  previous(){    
    this.estadoNextdeshabilitado=false
    this.page.number--;
    this.getUsuarios();
  }

  numeroPaginador(nPagina:number){        
    this.page.number=nPagina - 1;
    let cont = this.page.number + 1 ;
    if(this.page.totalPages > cont){      
      this.estadoNextdeshabilitado=false    
    }else{
      this.estadoNextdeshabilitado=true;
    }
    
    this.getUsuarios();
    
  }
  getUltimaPagina(){
    this.page.number=this.page.totalPages - 1
    let cont = this.page.number + 1 ;
    if(this.page.totalPages > cont){

    }else{
      this.estadoNextdeshabilitado=true;
    }
    this.getUsuarios();
  }
  getPrimeraPagina(){{
    this.estadoNextdeshabilitado=false
    this.page.number=0        
    this.getUsuarios();
  }}  

  eliminar(id: number){
    this.usuarioService.eliminar(id)
    .subscribe((res: any) => {    
      this.page ={
        number:0,
        size:10,
        totalPages :0,
        totalElements:0,
        paginaActual : 0   
      }                    
      swal.fire({
        icon: 'success',
        text: 'Eliminación exitosa.',
      })                   
      this.getUsuarios();                        
      },
      (err) => swal.fire({
        icon: 'error',
        text: 'Ha ocurrido un error con la eliminación',
      })
    );  
  }

}
